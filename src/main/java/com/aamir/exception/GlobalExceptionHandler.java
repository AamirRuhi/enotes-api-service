package com.aamir.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	//other koi hamara exception nhi chal to ye exception chalega
	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<?>
	 * handleException(Exception e){
	 * //log.error("GlobalExceptionhandler::handleException ::",e.getMessage());
	 * return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	 * 
	 * }
	 */
	
//custom error or exception show krne ke liye  for getallcategory
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(Exception e){
		//log.error("GlobalExceptionhandler::handleException ::",e.getMessage());
		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e){	
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	
	//validation kiya to excep aya MethodArgumentNotValidException with the help of annotaion first way
	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * ResponseEntity<?>
	 * handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
	 * Map<String ,Object> mp=new HashMap<>(); List<String> errors=
	 * ex.getBindingResult() .getFieldErrors() .stream()
	 * .map(er->er.getDefaultMessage()) .collect(Collectors.toList());
	 * mp.put("errors", errors); return new
	 * ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	 * 
	 * }
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException e){	
		//use save category inplement me 
		return new ResponseEntity<>(e.getErrors(),HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(ExistDataException.class)
	public ResponseEntity<?> handleExistDataException(ExistDataException e){	
		//use save category inplement me 
		return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		
	}
	// post man se isActive agre string me other than true or false send kiye to HttpMessageNotReadableException excep aya to handle kro isse
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){	
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
}
