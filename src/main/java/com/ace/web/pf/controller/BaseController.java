package com.ace.web.pf.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ResourceLoader;

import com.ace.web.pf.util.CommonUtil;
import com.ace.web.pf.util.MessagesUtil;

public abstract class BaseController {
	


	@Autowired
	protected CommonUtil commonUtils;

	@Autowired
	protected MessagesUtil messagesUtils;

	@Autowired
	protected ResourceLoader resourceLoader;
	
	
}
