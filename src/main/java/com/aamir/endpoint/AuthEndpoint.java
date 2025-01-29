package com.aamir.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aamir.dto.LoginRequest;
import com.aamir.dto.UserRequest;

import jakarta.servlet.http.HttpServletRequest;
@RequestMapping("/api/v1/auth")
public interface AuthEndpoint {
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRequest userDto,HttpServletRequest request) throws Exception;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception;
	
	
}
