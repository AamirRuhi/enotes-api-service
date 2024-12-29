package com.aamir.exception;

import java.util.Map;

public class ValidationException extends RuntimeException{
	
	private Map<String,Object> error;

	
	
	//paramtriz constrictor using field
	public ValidationException(Map<String, Object> error) {
		super("validation failled");
		this.error = error;
	}
	
	//for getting errors then create exceptionHandler for this errors
	public Map<String ,Object> getErrors(){
		return error;
	}
	
	
	
	

}
