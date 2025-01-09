package com.aamir.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aamir.service.HomeService;
import com.aamir.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


//anyone can access no need to authenticate
@RestController
@RequestMapping("/api/v1/home")
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
	
@GetMapping("verify")
public ResponseEntity<?> verifyUserAccount(@RequestParam Integer uid ,@RequestParam String code) throws Exception {
	//homeService bnayenge ab
	boolean verifyAccount = homeService.verifyAccount(uid, code);
	if(verifyAccount) {
		return CommonUtil.createBuildResponseMessage("account verification success", HttpStatus.OK);
	}
    return CommonUtil.createErrorResponseMessage("invalid verification link", HttpStatus.BAD_REQUEST);
}

}
