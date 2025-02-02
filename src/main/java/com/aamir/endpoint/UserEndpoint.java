package com.aamir.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aamir.dto.PasswordChangeRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "User", description ="All the Authenticated User  Apis")
@RequestMapping("/api/v1/user")
public interface UserEndpoint {
	
	@Operation(summary = "Get user profile ",tags= {"User"},description = "get user profile")
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile();
	
	
	@Operation(summary = "User Account Password change ",tags= {"User"},description = "User Account Password change")
	@PostMapping("/change-password")
	 public ResponseEntity<?> chengePassword(@RequestBody PasswordChangeRequest passwordChangeRequest);
}
