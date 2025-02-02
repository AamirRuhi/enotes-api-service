package com.aamir.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aamir.dto.LoginRequest;
import com.aamir.dto.UserRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
//user-controller -->tags ko customize ke liye 
@Tag(name = "Authetication", description ="All the user Authentication Apis")
@RequestMapping("/api/v1/auth")
public interface AuthEndpoint {
	
//endpoint level pr
	// last me @ApiResponses
	@ApiResponses(value= {
			@ApiResponse(responseCode = "201",description = "Register Success"),
			@ApiResponse(responseCode = "500",description = "Internal server error"),
			@ApiResponse(responseCode = "400",description = "Bad Request")
	})
	@Operation(summary = "User register endpoint",tags= {"Authetication","Home"})//Authetication folder nhi mila to create ho gya swagger-ui pr
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserRequest userDto,HttpServletRequest request) throws Exception;
	
	
	@Operation(summary = "User login endpoint",tags= {"Authetication","Home"})

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception;
	
	
}
