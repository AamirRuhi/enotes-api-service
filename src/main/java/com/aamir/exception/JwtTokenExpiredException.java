package com.aamir.exception;

public class JwtTokenExpiredException extends RuntimeException{
  //contructor using user class
	public JwtTokenExpiredException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
