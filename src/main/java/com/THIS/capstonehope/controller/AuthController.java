package com.THIS.capstonehope.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@GetMapping("/")
	public String home() {
		return "Hello, Home!";
	}
	@GetMapping("/secured")
	public String secured() {
		return "hello, secured";
	}
	
}
