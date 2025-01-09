package com.aamir.dto;

import lombok.Builder;
import lombok.Data;

//login ke response me token,user denge 
@Builder
@Data
public class LoginResponse {
private UserDto user;
private String token;
}
