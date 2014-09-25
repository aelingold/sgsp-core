package org.ucema.sgsp.controller;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;

public class CustomConnectController extends ConnectController {

	public CustomConnectController(
			ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	protected String connectView(String providerId) {
		return "redirect:/dashboard/socialmedia";
	}

	protected String connectedView(String providerId) {
		return "redirect:/dashboard/socialmedia";
	}
}
