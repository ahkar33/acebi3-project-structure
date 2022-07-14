package com.ace.web.pf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
@ComponentScan
@EnableAutoConfiguration
public class Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/", "classpath:static/").setCachePeriod(0)
				.resourceChain(false);
	}
}
