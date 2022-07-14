package com.ace.web.pf.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, AccessDeniedException {
		boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		if(ajax) {
			throw accessDeniedException;
		}
		response.sendRedirect("/common/accessDenied");
	}

}
