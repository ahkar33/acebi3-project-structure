package com.ace.web.pf.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setConcurrentRefresh(true);
		// # -1 : never reload, 0 always reload
		messageSource.setCacheSeconds(0);
		return messageSource;
	}
}