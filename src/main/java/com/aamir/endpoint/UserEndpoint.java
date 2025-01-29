package com.aamir.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aamir.dto.PasswordChangeRequest;
@RequestMapping("/api/v1/user")
public interface UserEndpoint {
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile();
	
	@PostMapping("/change-password")
	 public ResponseEntity<?> chengePassword(@RequestBody PasswordChangeRequest passwordChangeRequest);
}
