package org.ucema.sgsp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.ucema.sgsp.api.dto.ContactMessageDTO;
import org.ucema.sgsp.service.ContactMessageService;

@Controller
public class ContactMessageController {

	@Autowired
	private ContactMessageService contactMessageService;

	@RequestMapping(value = "/contact-messages/{id}", method = RequestMethod.GET)
	public @ResponseBody ContactMessageDTO get(@PathVariable Long id) {
		return contactMessageService.get(id);
	}

	@RequestMapping(value = "/contact-messages", method = RequestMethod.GET)
	public @ResponseBody List<ContactMessageDTO> list() {
		return contactMessageService.list();
	}

	@RequestMapping(value = "/contact-messages", method = RequestMethod.POST)
	public String saveOrUpdate(
			@Valid @ModelAttribute("contactMessage") ContactMessageDTO contactMessage,
			BindingResult result, WebRequest request, Model model) {
		
		contactMessageService.saveOrUpdate(contactMessage);
		
		return "redirect:/";
	}
}
