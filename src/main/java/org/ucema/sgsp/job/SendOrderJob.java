package org.ucema.sgsp.job;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ucema.sgsp.api.dto.OrderDTO;
import org.ucema.sgsp.api.dto.QuoteDTO;
import org.ucema.sgsp.api.dto.UserDTO;
import org.ucema.sgsp.api.dto.UserNotifyDTO;
import org.ucema.sgsp.persistence.model.QuoteStatusType;
import org.ucema.sgsp.persistence.model.UserNotifyType;
import org.ucema.sgsp.service.MailService;
import org.ucema.sgsp.service.OrderService;
import org.ucema.sgsp.service.QuoteService;
import org.ucema.sgsp.service.UserNotifyService;
import org.ucema.sgsp.service.UserService;

import com.google.common.collect.Lists;

@Component
public class SendOrderJob {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SendOrderJob.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserNotifyService userNotifyService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private MailService mailService;
	@Resource
	private Environment env;

	@Scheduled(fixedRate = 30000)
	public void sendOrders() {
		LOGGER.info("Sending orders for quotation");

		List<OrderDTO> pendingOrders = orderService.list(true);

		LOGGER.info("Pending Orders for quotation[" + pendingOrders.size()
				+ "]");

		pendingOrders.forEach(order -> {
			persistData(order);
		});
	}

	@Transactional
	public void persistData(OrderDTO order) {

		List<String> workAreaCodes = new ArrayList<String>();
		workAreaCodes.add(order.getWorkAreaCode());

		// List<UserDTO> users = userService
		// .findByWorkAreas_CodeAndIsEnabledAndIsProfessional(
		// workAreaCodes, true, true);

		List<UserDTO> users = userService
				.findByWorkAreas_CodeAndIsEnabledAndIsProfessionalAndUserWorkZones_City_Code(
						workAreaCodes, true, true,
						Lists.newArrayList(order.getCityCode()));

		users = users.stream()
				.filter(u -> !u.getEmail().equals(order.getUsername()))
				.collect(Collectors.toList());

		users.forEach(user -> {

			if (env.getProperty("send.email.enabled", "false").equals("true")) {
				mailService.sendEmail(user.getEmail(),
						buildMessage(user, order));
			}

			userNotifyService.saveOrUpdate(UserNotifyDTO.newInstance()
					.withOrderId(order.getId())
					.withType(UserNotifyType.EMAIL.name())
					.withUsername(user.getEmail()).build());

			quoteService.saveOrUpdate(QuoteDTO.newInstance()
					.withOrderId(order.getId()).withUsername(user.getEmail())
					.withStatusType(QuoteStatusType.PENDING.name()));
		});

		LOGGER.info("Users returned[" + users.size() + "] with workAreaCodes["
				+ workAreaCodes + "]");

		if (users.size() > 0) {
			order.setPendingNotify(false);
			orderService.update(order);
		}
	}

	private String buildMessage(UserDTO user, OrderDTO order) {

		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}
}
