package com.aamir.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.aamir.entity.User;

public interface JwtService {
//ek method for creating token
public String generateToken(User user);

//token ke help se username get krne ke liye
public String extractUsername(String token);

//for validate token
public Boolean validateToken(String token,UserDetails userDetails);

	
	
}
