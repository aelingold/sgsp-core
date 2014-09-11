package org.ucema.sgsp.job;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.service.OrderService;
import org.ucema.sgsp.service.UserService;

@Component
public class SendOrderJob {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SendOrderJob.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	@Scheduled(fixedRate = 5000)
	public void sendOrders() {
		LOGGER.info("Sending orders for quotation");

		List<OrderDTO> pendingOrders = orderService.list(true);

		LOGGER.info("Pending Orders for quotation[" + pendingOrders.size()
				+ "]");

		pendingOrders.forEach(po -> {

			List<String> workAreaCodes = new ArrayList<String>();
			workAreaCodes.add(po.getWorkAreaCode());

			List<UserDTO> users = userService.findByWorkAreas_CodeAndIsEnabled(
					workAreaCodes, true);

			LOGGER.info("Users returned[" + users.size()
					+ "] with workAreaCodes[" + workAreaCodes + "]");

			po.setPendingNotify(false);
			orderService.update(po);
		});
	}
}
