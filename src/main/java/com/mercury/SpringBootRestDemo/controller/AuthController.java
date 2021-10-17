package com.mercury.SpringBootRestDemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.SpringBootRestDemo.http.Response;
import com.mercury.SpringBootRestDemo.service.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;

	//after log in send another request to get current login information
	@GetMapping("/checklogin")
	//Authentication don't need autowire
	//it will return object
	public Response checklogin(Authentication authentication) {
		return authService.checklogin(authentication);
	}
}
