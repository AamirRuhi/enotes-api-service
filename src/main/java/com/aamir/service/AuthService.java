package com.aamir.service;

import com.aamir.dto.LoginRequest;
import com.aamir.dto.LoginResponse;
import com.aamir.dto.UserRequest;

public interface AuthService {
public boolean register(UserRequest userDto,String url) throws Exception;

//loginrequest accept krna h loginresponse responce dena hai
public LoginResponse login(LoginRequest loginRequest);
}
