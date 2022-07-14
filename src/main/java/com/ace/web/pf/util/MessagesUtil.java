package com.ace.web.pf.util;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import com.ace.web.pf.config.MessageConfig;

@Component
public class MessagesUtil {

	@Autowired
	private MessageConfig messageConfig;

	private MessageSourceAccessor accessor;

	@PostConstruct
	private void init() {
		Locale locale = LocaleContextHolder.getLocale();
		accessor = new MessageSourceAccessor(messageConfig.messageSource(), locale);
	}

	public String getMessage(String code) {
		return accessor.getMessage(code);
	}

	public String getMessage(String code, Object... param) {
		return accessor.getMessage(code, param);
	}
}
