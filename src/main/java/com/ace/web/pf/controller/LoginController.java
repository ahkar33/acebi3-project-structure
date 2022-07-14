package com.ace.web.pf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ace.web.pf.enums.PageUrls;
import com.ace.web.pf.service.impl.UserService;


@Controller
public class LoginController extends BaseController {

	@Autowired
	@Qualifier("userService")
	protected UserService userServiceImpl;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(PageUrls.Login.value());
		return modelAndView;
	}

}
