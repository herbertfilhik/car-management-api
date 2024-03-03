package com.management.controller;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MessageController {
	
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
	
	@GetMapping("/message")
	public String getMessage() {
		return "Hello People";
	}
	

}
