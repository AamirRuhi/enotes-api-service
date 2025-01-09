package com.aamir.dto;

import lombok.Data;
// login krne ke liye kya kya variable lenge LoginRequest me
//fir loginResponce bnayenge jwt response medene wale h dto me hi ,jisme user or token denge
@Data
public class LoginRequest {
private String email;
private String password;
}
