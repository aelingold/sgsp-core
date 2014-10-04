package org.ucema.sgsp.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ucema.sgsp.api.dto.AmountDTO;
import org.ucema.sgsp.api.dto.PaymentDTO;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.dto.RatePlanDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.api.dto.UserWorkRateDTO;
import org.ucema.sgsp.persistence.model.OrderStatusType;
import org.ucema.sgsp.persistence.model.PaymentStatusType;
import org.ucema.sgsp.persistence.model.QuoteStatusType;

@Service
public class DashBoardRatingService {

	@Autowired
	private UserService userService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private UserWorkRateService userWorkRateService;
	@Autowired
	private RatePlanService ratePlanService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MailService mailService;

	@Transactional
	public void save(UserWorkRateDTO userWorkRate) {

		userWorkRateService.update(userWorkRate);

		QuoteDTO quoteDTO = quoteService.get(userWorkRate.getQuoteId());

		UserDTO user = userService.findByEmail(quoteDTO.getUsername());

		String ratePlanCode = user.getRatePlanCode();

		RatePlanDTO ratePlan = ratePlanService.findByCode(ratePlanCode);

		if (userWorkRate.getWorkCompleted()) {

			orderService.updateStatus(quoteDTO.getOrderId(),
					OrderStatusType.FINISHED);

			List<QuoteDTO> quotes = quoteService.findByOrder_Id(quoteDTO
					.getOrderId());
			quotes = quotes.stream()
					.filter(q -> !q.getId().equals(quoteDTO.getId()))
					.collect(Collectors.toList());

			quoteService.updateStatus(quoteDTO.getId(),
					QuoteStatusType.DONE);

			quoteService.updateStatus(quotes.stream().map(q -> q.getId())
					.collect(Collectors.toList()), QuoteStatusType.CANCELLED);

			if (ratePlanCode.equals(RatePlanDTO.PLAN2)) {

				PaymentDTO payment = new PaymentDTO();
				payment.setStatusType(PaymentStatusType.DONE.name());
				payment.setQuoteId(userWorkRate.getQuoteId());
				payment.setUsername(quoteDTO.getUsername());
				payment.setAmount(ratePlan.getAmount());
				paymentService.saveOrUpdate(payment);

			} else if (ratePlanCode.equals(RatePlanDTO.PLAN3)) {

				PaymentDTO payment = new PaymentDTO();
				payment.setStatusType(PaymentStatusType.DONE.name());
				payment.setQuoteId(userWorkRate.getQuoteId());
				payment.setUsername(quoteDTO.getUsername());

				BigDecimal amount = BigDecimal.ZERO;
				String currencyCode = "";

				if (quoteDTO.getAmount() != null
						&& quoteDTO.getAmount().getAmount() != null) {
					amount = quoteDTO.getAmount().getAmount();
					currencyCode = quoteDTO.getAmount().getCurrency().getCode();
				} else {
					amount = quoteDTO.getVisitAmount().getAmount();
					currencyCode = quoteDTO.getVisitAmount().getCurrency()
							.getCode();
				}

				AmountDTO amountDTO = AmountDTO
						.newInstance()
						.withAmount(
								amount.multiply(ratePlan
										.getPercentageQuantity()))
						.withCurrencyCode(currencyCode);

				payment.setAmount(amountDTO);
				paymentService.saveOrUpdate(payment);
			}
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("firstName", quoteDTO.getOrder().getFirstName());
		model.put("lastName", quoteDTO.getOrder().getLastName());

		mailService.sendEmail(quoteDTO.getUsername(), MailService.FROM_EMAIL,
				"Calificacion recibida", "mail/userWorkRateResponse.ftl", model);		
	}
}
