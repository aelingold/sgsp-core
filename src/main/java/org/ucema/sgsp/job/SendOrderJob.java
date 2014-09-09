package org.ucema.sgsp.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SendOrderJob {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SendOrderJob.class);

	//@Scheduled(fixedRate = 5000)
	public void sendOrders() {
		LOGGER.info("Sending orders for quotation");
	}
}
