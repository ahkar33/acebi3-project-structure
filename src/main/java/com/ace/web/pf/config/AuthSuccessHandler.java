package com.ace.web.pf.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ace.web.pf.enums.AuthorityName;

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final Integer SESSION_TIMEOUT_IN_SECONDS = 60 * 240;

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {

		request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
		// Get the role of logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();

		String targetUrl = "";
		if (role.contains(AuthorityName.ADMIN.toString())) {
			targetUrl = "/generalSetting/student";
		} 
		return targetUrl;
	}
}
