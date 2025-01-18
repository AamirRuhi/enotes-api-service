package com.aamir.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aamir.dto.PasswordResetRequest;
import com.aamir.service.HomeService;
import com.aamir.service.UserService;
import com.aamir.util.CommonUtil;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//slf4j use krenge jo lombok me available
@RestController
@RequestMapping("/api/v1/home")
public class HomeController {
	
	 Logger log=LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private HomeService homeService;

	@Autowired
	private UserService userService;

	@GetMapping("verify")
	public ResponseEntity<?> verifyUserAccount(@RequestParam Integer uid, @RequestParam String code) throws Exception {
		log.info("HomeController : verifyUserAccount() : Execution start");
		boolean verifyAccount = homeService.verifyAccount(uid, code);//homeserviceImpl me @slf4j use kro
		if (verifyAccount) {
			return CommonUtil.createBuildResponseMessage("account verification success", HttpStatus.OK);
		}
		log.info("HomeController : verifyUserAccount() : Execution End");
		return CommonUtil.createErrorResponseMessage("invalid verification link", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/send-email-reset")
	public ResponseEntity<?> sendEmailForPasswordReset(@RequestParam String email, HttpServletRequest request)
			throws Exception { // userservice me method create krenge
		userService.sendEmailPasswordReset(email, request);

		return CommonUtil.createBuildResponseMessage("email sent success check email for reset password",
				HttpStatus.OK);

	}

	@GetMapping("/verify-password-link")
	public ResponseEntity<?> verifyPasswordResetLink(@RequestParam Integer uid, @RequestParam String code)
			throws Exception {
		userService.verifypswdResetLing(uid, code);

		return CommonUtil.createBuildResponseMessage("email verified success", HttpStatus.OK);

	}
  
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) throws Exception {
    userService.resetPassword(passwordResetRequest);
    
	return CommonUtil.createBuildResponseMessage("password reset successfully", HttpStatus.OK);
	
	}

}
