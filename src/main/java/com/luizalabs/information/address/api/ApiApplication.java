package com.luizalabs.information.address.api;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.retry.annotation.EnableRetry;

@Generated
@EnableCaching
@EnableRetry
@EnableFeignClients
@SpringBootApplication
public class ApiApplication {

	private static Logger logger = LoggerFactory.getLogger(ApiApplication.class);
	
	public static void main(final String[] args) {
		
		final Environment env = SpringApplication.run(ApiApplication.class, "").getEnvironment();
		
		env.getProperty("spring.application.name");
		env.getProperty("server.port");
		env.getProperty("server.servlet.contextPath");
		env.getProperty("info.build.version");
		env.getProperty("spring.profiles.active");
		
		logger.info("Application {} version {} is running on port {}",
				env.getProperty("spring.application.name"),
				env.getProperty("info.build.version"),
				env.getProperty("server.port"));
	}

}
