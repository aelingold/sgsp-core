package org.ucema.sgsp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.ucema.sgsp.service.WorkAreaItemService;
import org.ucema.sgsp.service.WorkAreaQuestionService;
import org.ucema.sgsp.service.WorkAreaService;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    protected static final String VIEW_NAME_HOMEPAGE = "index";
    @Autowired
	private WorkAreaService workAreaService;
    @Autowired
	private WorkAreaItemService workAreaItemService;
    @Autowired
    private WorkAreaQuestionService workAreaQuestionService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showHomePage(WebRequest request, Model model) {
        LOGGER.debug("Rendering homepage.");
        
        model.addAttribute("workAreas", workAreaService.list());
        model.addAttribute("workAreaItems", workAreaItemService.list());
        
        model.addAttribute("workAreaQuestions", workAreaQuestionService.list());
        
        return VIEW_NAME_HOMEPAGE;
    }
}
