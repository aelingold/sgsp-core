package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.NotifyDTO;
import org.ucema.sgsp.service.NotifyService;

@Controller
public class NotifyController {

	@Autowired
	private NotifyService notifyService;

	@RequestMapping(value = "/notifies/{id}", method = RequestMethod.GET)
	public @ResponseBody NotifyDTO get(@PathVariable Long id) {
		return notifyService.get(id);
	}
	
	@RequestMapping(value = "/notifies", method = RequestMethod.GET)
	public @ResponseBody List<NotifyDTO> list() {
		return notifyService.list();
	}

	@RequestMapping(value = "/notifies", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(@RequestBody NotifyDTO notify) {
		notifyService.saveOrUpdate(notify);
	}

	@RequestMapping(value = "/notifies/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		notifyService.delete(id);
	}
}
