package com.ace.web.pf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {
	@Bean
    public LoggingFilter logFilter() {
        LoggingFilter filter
                = new LoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(true);

        return filter;
    }
}
