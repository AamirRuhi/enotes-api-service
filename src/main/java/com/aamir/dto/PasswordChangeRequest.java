package com.aamir.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeRequest {
//password ko change krne ke liye user se hum le rhe hai
	
	private String oldPassword;
	
	private String newPassword;
	
}
