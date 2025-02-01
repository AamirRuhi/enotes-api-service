package com.aamir.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aamir.dto.PasswordChangeRequest;
import com.aamir.dto.UserResponse;
import com.aamir.endpoint.UserEndpoint;
import com.aamir.entity.User;
import com.aamir.service.UserService;
import com.aamir.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@RestController

public class UserController implements UserEndpoint{
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public ResponseEntity<?> getProfile(){
	 User loggedInUser = CommonUtil.getLoggedInUser();
	 //abhi ye entity type hai ise convert krna hai UserRespone me mapper ke help se
	 UserResponse userResponse = modelMapper.map(loggedInUser, UserResponse.class);
	 
		return CommonUtil.createBuildResponse(userResponse, HttpStatus.OK);
		
	}
 @Override
 public ResponseEntity<?> chengePassword( PasswordChangeRequest passwordChangeRequest){
	 userService.changePassword(passwordChangeRequest);
	 
	 return CommonUtil.createBuildResponseMessage("Password changed successfully", HttpStatus.OK);
	 
 }
 
 
}
