package com.aamir.service;

import com.aamir.dto.UserDto;

public interface UserService {
public boolean register(UserDto userDto,String url) throws Exception;
}
