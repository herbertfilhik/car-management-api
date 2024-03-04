package com.management.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MessageController {
	
	@GetMapping("/message")
	public String getMessage() {
		return "Hello People";
	}
}
