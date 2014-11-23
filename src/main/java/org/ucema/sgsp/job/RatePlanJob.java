package org.ucema.sgsp.job;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.AmountDTO;
import org.ucema.sgsp.api.dto.PaymentDTO;
import org.ucema.sgsp.api.dto.RatePlanDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.persistence.model.PaymentStatusType;
import org.ucema.sgsp.persistence.model.RatePlanPackageType;
import org.ucema.sgsp.service.PaymentService;
import org.ucema.sgsp.service.RatePlanService;
import org.ucema.sgsp.service.UserService;

@Component
public class RatePlanJob {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RatePlanJob.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RatePlanService ratePlanService;
	@Autowired
	private PaymentService paymentService;
	@Resource
	private Environment env;	

	@Scheduled(fixedRate = 30000)
	public void createPayments() {
		LOGGER.info("Creating payments for users");

		List<UserDTO> users = userService
				.findByUserRatePlan_RatePlan_PackageTypeAndIsProfessionalAndIsEnabled(
						RatePlanPackageType.FIXED, true, true);

		LOGGER.info("Retrieved users[" + users.size() + "]");

		users.forEach(user -> {
			persistData(user);
		});
	}

	@Transactional
	public void persistData(UserDTO user) {

		String ratePlanCode = user.getRatePlanCode();

		List<PaymentDTO> userPayments = paymentService
				.findByUser_EmailOrderByCreatedAtDesc(user.getEmail());

		if (userPayments.size() == 0) {

			RatePlanDTO ratePlan = ratePlanService.findByCode(ratePlanCode);

			paymentService.saveOrUpdate(buildPayment(ratePlan.getAmount(),
					user.getEmail()));

		} else {

			PaymentDTO lastPayment = userPayments.get(0);

			DateTime start = new DateTime(lastPayment.getCreatedAt().getTime())
					.withDayOfMonth(1);
			DateTime end = new DateTime().withDayOfMonth(1);
			int months = Months.monthsBetween(start, end).getMonths();

			if (months > 0) {
				RatePlanDTO ratePlan = ratePlanService.findByCode(ratePlanCode);

				paymentService.saveOrUpdate(buildPayment(ratePlan.getAmount(),
						user.getEmail()));
			}
		}
	}

	public PaymentDTO buildPayment(AmountDTO amount, String email) {

		PaymentDTO payment = new PaymentDTO();

		payment.setAmount(amount);
		payment.setStatusType(PaymentStatusType.DONE.name());
		payment.setUsername(email);
		
		Integer allowedDays = env.getProperty("payment.allowed.days.qty",Integer.class, 14);
		payment.setPaymentDateAllowedBefore(new DateTime().plusDays(allowedDays).toDate());
		
		return payment;
	}
}
