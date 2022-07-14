package com.ace.web.pf.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ModelAndView handleAllExceptions(Exception e, WebRequest request) {
		ModelAndView mv = new ModelAndView("error/error.html");
		mv.addObject("message", e.getMessage());
		return mv;

	}

}
