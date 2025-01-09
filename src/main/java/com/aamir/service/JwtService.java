package com.aamir.service;

import com.aamir.entity.User;

public interface JwtService {
//ek method for creating token
public String generateToken(User user);
	
	
}
