package com.aamir.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequest {
// kaha se email jane wala hai
	private String to;
	
	private String subject;
	
	private String title;
	
	private String message;
}
