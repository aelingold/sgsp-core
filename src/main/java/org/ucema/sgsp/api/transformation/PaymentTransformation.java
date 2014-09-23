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

@Component
public class PaymentTransformation {

	@Autowired
	private QuoteTransformation quoteTransformation;
	@Autowired
	private AmountTransformation amountTransformation;	

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

		return result;
	}

	public Payment transformToModel(PaymentDTO payment) {
		Payment result = new Payment();

		result.setId(payment.getId());
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

		return result;
	}
}
