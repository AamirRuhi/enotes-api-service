package com.aamir.exception;

public class SuccessException extends RuntimeException {
	
public SuccessException(String message) {
	//Globalexceptionhandler me is exception ka handler bna lenge ,jab ye exception aye to wo handler chale
	super(message);
}
}
