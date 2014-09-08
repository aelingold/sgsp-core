package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.WorkAreaItemDTO;
import org.ucema.sgsp.service.WorkAreaItemService;

@Controller
public class WorkAreaItemController {

	@Autowired
	private WorkAreaItemService workAreaItemService;

	@RequestMapping(value = "/work-area-items/{id}", method = RequestMethod.GET)
	public @ResponseBody WorkAreaItemDTO get(@PathVariable Long id) {
		return workAreaItemService.get(id);
	}
	
	@RequestMapping(value = "/work-area-items", method = RequestMethod.GET)
	public @ResponseBody List<WorkAreaItemDTO> list() {
		return workAreaItemService.list();
	}

	@RequestMapping(value = "/work-area-items", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(@RequestBody WorkAreaItemDTO workAreaItem) {
		workAreaItemService.saveOrUpdate(workAreaItem);
	}

	@RequestMapping(value = "/work-area-items/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		workAreaItemService.delete(id);
	}
}
