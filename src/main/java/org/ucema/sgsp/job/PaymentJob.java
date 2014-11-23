package org.ucema.sgsp.job;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.PaymentDTO;
import org.ucema.sgsp.persistence.model.PaymentStatusType;
import org.ucema.sgsp.service.PaymentService;

@Component
public class PaymentJob {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PaymentJob.class);

	@Autowired
	private PaymentService paymentService;

	@Autowired
	@Scheduled(fixedRate = 30000)
	public void verifyingPayments() {
		LOGGER.info("Verifying payments for users");

		List<PaymentDTO> payments = paymentService
				.findByStatusType(PaymentStatusType.PENDING);

		LOGGER.info("Retrieved pending payments[" + payments.size() + "]");

		payments.forEach(payment -> {

			if (payment.getPaymentDateAllowedBefore().before(new Date())) {
				updateData(payment);
			}
		});
	}

	private void updateData(PaymentDTO payment) {
		payment.setStatusType(PaymentStatusType.EXPIRED.name());
		paymentService.saveOrUpdate(payment);
	}
}
