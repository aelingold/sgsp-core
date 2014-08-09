package org.ucema.sgsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ucema.sgsp.api.dto.WorkAreaDTO;
import org.ucema.sgsp.service.WorkAreaService;

@Controller
public class WorkAreaController {

	@Autowired
	private WorkAreaService workAreaService;

	@RequestMapping(value = "/workAreas/{id}", method = RequestMethod.GET)
	public @ResponseBody WorkAreaDTO get(@PathVariable Long id) {
		return workAreaService.get(id);
	}
	
	@RequestMapping(value = "/workAreas", method = RequestMethod.GET)
	public @ResponseBody List<WorkAreaDTO> list() {
		return workAreaService.list();
	}

	@RequestMapping(value = "/workAreas", method = RequestMethod.POST)
	public @ResponseBody void saveOrUpdate(@RequestBody WorkAreaDTO workArea) {
		workAreaService.saveOrUpdate(workArea);
	}

	@RequestMapping(value = "/workAreas/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
		workAreaService.delete(id);
	}
}
