package com.management.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.management.controller.MessageController;

@SpringBootApplication
public class CarManagementApiApplication {
	
    private static final Logger log = LoggerFactory.getLogger(CarManagementApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CarManagementApiApplication.class, args);
	}

}
