package com.aamir.dto;

import lombok.Builder;
import lombok.Data;

//login ke response me token,user denge 
@Builder
@Data
public class LoginResponse {
private UserRequest user;
private String token;
}
