package com.aamir.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aamir.dto.PasswordResetRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
@Tag(name = "Home", description ="All the Home  Apis")
@RequestMapping("/api/v1/home")
public interface HomeEndpoint {

	@Operation(summary = "Verification User Account ",tags= {"Home"},description = "User Account Verification after register account")
	@GetMapping("verify")
	public ResponseEntity<?> verifyUserAccount(@RequestParam Integer uid, @RequestParam String code) throws Exception;
	
	@Operation(summary = "Send Email for Password Reset ",tags= {"Home"},description = "User can send Email for password reset")
	@GetMapping("/send-email-reset")
	public ResponseEntity<?> sendEmailForPasswordReset(@RequestParam String email, HttpServletRequest request)
			throws Exception;
	
	@Operation(summary = "Verify password reset link ",tags= {"Home"},description = "User verify password link")
	@GetMapping("/verify-password-link")
	public ResponseEntity<?> verifyPasswordResetLink(@RequestParam Integer uid, @RequestParam String code)
			throws Exception;
	
	@Operation(summary = "Reset Password ",tags= {"Home"},description = "User can do Password reset")
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) throws Exception;
}
