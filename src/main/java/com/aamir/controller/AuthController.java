package com.aamir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aamir.dto.LoginRequest;
import com.aamir.dto.LoginResponse;
import com.aamir.dto.UserRequest;
import com.aamir.service.AuthService;
import com.aamir.util.CommonUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRequest userDto,HttpServletRequest request) throws Exception
	{
		log.info("AuthController : registerUser() : execution start");

		//"http://localhost:8081/api/v1/home/verify?uid isko dynamic krne ke liye HttpServletRequest request ,request me url get krenge
		String url =CommonUtil.getUrl(request);
		//HttpServletRequest request big usr ko dynamic krne ke liye ,CommonUtil me
		
		boolean register = authService.register(userDto,url);
		if(!register) {
			log.info("error : {}","register failled");
			return CommonUtil.createErrorResponseMessage("register failled", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("AuthController : registerUser() : execution end");
		return CommonUtil.createBuildResponseMessage("register success", HttpStatus.CREATED);


		
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception
	//hr resource ko access krne ke liye bar bar username,password deni ki need nhi isliye login method bnaya
	//loginRequest bnagee dto me kya request krne wale hai
	{
		LoginResponse loginResponse = authService.login(loginRequest);
		if(ObjectUtils.isEmpty(loginResponse))
		{
			return CommonUtil.createErrorResponseMessage("invalid credential", HttpStatus.BAD_REQUEST);//isko globalexception me handle krenge
		}
		
		return CommonUtil.createBuildResponse(loginResponse, HttpStatus.OK);
				
	}

}
