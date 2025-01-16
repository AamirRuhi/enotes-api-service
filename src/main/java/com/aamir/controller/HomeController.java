package com.aamir.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aamir.dto.PasswordResetRequest;
import com.aamir.service.HomeService;
import com.aamir.service.UserService;
import com.aamir.util.CommonUtil;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//anyone can access no need to authenticate
@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

	@Autowired
	private HomeService homeService;

	@Autowired
	private UserService userService;

	@GetMapping("verify")
	public ResponseEntity<?> verifyUserAccount(@RequestParam Integer uid, @RequestParam String code) throws Exception {
		// homeService bnayenge ab
		boolean verifyAccount = homeService.verifyAccount(uid, code);
		if (verifyAccount) {
			return CommonUtil.createBuildResponseMessage("account verification success", HttpStatus.OK);
		}
		return CommonUtil.createErrorResponseMessage("invalid verification link", HttpStatus.BAD_REQUEST);
	}

//password Reset krne ke liye yaha qki home ke baad authentication check nhi hoga as mentioned in securityconfig
	@GetMapping("/send-email-reset")
	public ResponseEntity<?> sendEmailForPasswordReset(@RequestParam String email, HttpServletRequest request)
			throws Exception { // userservice me method create krenge
		userService.sendEmailPasswordReset(email, request);

		return CommonUtil.createBuildResponseMessage("email sent success check email for reset password",
				HttpStatus.OK);

	}

//  Change my password click ke baad jo link aya http://localhost:8081/api/v1/home/verify-password-link?uid=22&&code=194aa8ce-3b0b-4e3d-997a-fa2f61af691e
//user veryfy krenge uid or code ko get krna hai by requestparam
	@GetMapping("/verify-password-link")
	public ResponseEntity<?> verifyPasswordResetLink(@RequestParam Integer uid, @RequestParam String code)
			throws Exception {
		userService.verifypswdResetLing(uid, code);

		return CommonUtil.createBuildResponseMessage("email verified success", HttpStatus.OK);

	}
  
	//yaha user ka uid or new password dena hai to dto me PasswordResetRequest bnalenge
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) throws Exception {
    userService.resetPassword(passwordResetRequest);
    
	return CommonUtil.createBuildResponseMessage("password reset successfully", HttpStatus.OK);
	/*
	 * { "uid":22, "newPassword":"Tipu@123" } body me denge
	 */
	}

}
