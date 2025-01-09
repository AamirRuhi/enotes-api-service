package com.aamir.service;

import com.aamir.dto.LoginRequest;
import com.aamir.dto.LoginResponse;
import com.aamir.dto.UserDto;

public interface UserService {
public boolean register(UserDto userDto,String url) throws Exception;

//loginrequest accept krna h loginresponse responce dena hai
public LoginResponse login(LoginRequest loginRequest);
}
