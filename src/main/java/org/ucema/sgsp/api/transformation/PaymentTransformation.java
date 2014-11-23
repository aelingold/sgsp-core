package org.ucema.sgsp.api.transformation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.PaymentDTO;
import org.ucema.sgsp.persistence.model.Payment;
import org.ucema.sgsp.persistence.model.PaymentStatusType;
import org.ucema.sgsp.persistence.model.PaymentType;
import org.ucema.sgsp.persistence.model.Quote;
import org.ucema.sgsp.security.model.User;
import org.ucema.sgsp.service.UserService;

@Component
public class PaymentTransformation {

	@Autowired
	private QuoteTransformation quoteTransformation;
	@Autowired
	private AmountTransformation amountTransformation;
	@Autowired
	private UserService userService;

	public List<PaymentDTO> transformToApi(List<Payment> payments) {
		List<PaymentDTO> result = new ArrayList<PaymentDTO>();

		for (Payment payment : payments) {
			result.add(transformToApi(payment));
		}

		return result;
	}

	public List<Payment> transformToModel(List<PaymentDTO> payments) {
		List<Payment> result = new ArrayList<Payment>();

		for (PaymentDTO payment : payments) {
			result.add(transformToModel(payment));
		}

		return result;
	}

	public PaymentDTO transformToApi(Payment payment) {
		PaymentDTO result = new PaymentDTO();

		result.setId(payment.getId());
		result.setCreatedAt(payment.getCreatedAt());
		result.setUpdatedAt(payment.getUpdatedAt());
		if (payment.getQuote() != null) {
			result.setQuoteId(payment.getQuote().getId());
		}
		
		if (payment.getPaymentType() != null){
			result.setPaymentType(payment.getPaymentType().name());	
		}
		result.setStatusType(payment.getStatusType().name());
		
		if (payment.getAmount() != null && payment.getAmount().getAmount() != null){
			result.setAmount(amountTransformation.buildAmount(payment.getAmount()));	
		}
		
		if (payment.getUser() != null){
			result.setUserId(payment.getUser().getId());
			result.setUsername(payment.getUser().getEmail());
			result.setUserEnabled(payment.getUser().getIsEnabled());
		}
		
		result.setPaymentDateAllowedBefore(payment.getPaymentDateAllowedBefore());

		return result;
	}

	public Payment transformToModel(PaymentDTO payment) {
		Payment result = new Payment();

		result.setId(payment.getId());
		result.setCreatedAt(payment.getCreatedAt());
		result.setUpdatedAt(payment.getUpdatedAt());		
		
		if (payment.getQuoteId() != null) {
			result.setQuote(new Quote(payment.getQuoteId()));
		}
		
		if (payment.getPaymentType() != null){
			result.setPaymentType(PaymentType.valueOf(payment.getPaymentType()));	
		}
		result.setStatusType(PaymentStatusType.valueOf(payment.getStatusType()));
		
		if (payment.getAmount() != null && payment.getAmount().getAmount() != null){
			result.setAmount(amountTransformation.buildAmount(payment.getAmount()));	
		}
		
		if (payment.getUserId() != null){
			result.setUser(new User(payment.getUserId()));
		}
		
		if (payment.getUsername() != null){
			result.setUser(new User(userService.findByEmail(payment.getUsername()).getId()));
		}
		
		result.setPaymentDateAllowedBefore(payment.getPaymentDateAllowedBefore());

		return result;
	}
}
