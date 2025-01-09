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

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<?> registerUser(@RequestBody UserDto userDto,HttpServletRequest request) throws Exception
	{
		//"http://localhost:8081/api/v1/home/verify?uid isko dynamic krne ke liye HttpServletRequest request ,request me url get krenge
		String url =CommonUtil.getUrl(request);
		//HttpServletRequest request big usr ko dynamic krne ke liye ,CommonUtil me
		
		boolean register = userService.register(userDto,url);
		if(register) {
			return CommonUtil.createBuildResponseMessage("register success", HttpStatus.CREATED);
		}
		return CommonUtil.createErrorResponseMessage("register failled", HttpStatus.INTERNAL_SERVER_ERROR);

		
	}
	
}
