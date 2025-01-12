package com.aamir.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aamir.dto.UserResponse;
import com.aamir.entity.User;
import com.aamir.util.CommonUtil;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
 @GetMapping("/profile")
	public ResponseEntity<?> getProfile(){
	 User loggedInUser = CommonUtil.getLoggedInUser();
	 //abhi ye entity type hai ise convert krna hai UserRespone me mapper ke help se
	 UserResponse userResponse = modelMapper.map(loggedInUser, UserResponse.class);
	 
		return CommonUtil.createBuildResponse(userResponse, HttpStatus.OK);
		
	}
}
