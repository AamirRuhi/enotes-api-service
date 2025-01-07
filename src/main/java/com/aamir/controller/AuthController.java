package com.aamir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aamir.dto.UserDto;
import com.aamir.service.UserService;
import com.aamir.util.CommonUtil;

@RestController
@RequestMapping("/api/v1/user")
public class AuthController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<?> registerUser(@RequestBody UserDto userDto)
	{
		boolean register = userService.register(userDto);
		if(register) {
			return CommonUtil.createBuildResponseMessage("register success", HttpStatus.CREATED);
		}
		return CommonUtil.createErrorResponseMessage("register failled", HttpStatus.INTERNAL_SERVER_ERROR);

		
	}
	
}
