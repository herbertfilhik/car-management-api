package com.management.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.management")
@EnableJpaRepositories(basePackages = "com.management.repository")
@EntityScan("com.management.model")
public class CarManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarManagementApiApplication.class, args);
	}

}
