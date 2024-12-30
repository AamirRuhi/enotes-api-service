package com.aamir.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aamir.handler.GenericResponse;

public class CommonUtil {
	
	// yaha 4 method bnalunga createBuildResponse,createBuildResponseMessage,createErrorResponse ,createErrorResponseMessage
	//data ke sath status send krna hai
	public static ResponseEntity<?> createBuildResponse(Object data, HttpStatus status) {
          //builder ke help se set krne wale h genericResponse me object ko
		GenericResponse response = GenericResponse.builder()
				.responseStatus(status)
				.status("succes")
				.message("succes")
				.data(data).build();
		return response.create();
	}

	// meg ke sath data bhi dena ghai to ye method call krenge
	public static ResponseEntity<?> createBuildResponseMessage(String message, HttpStatus status) {

		GenericResponse response = GenericResponse.builder()
				.responseStatus(status)
				.status("succes")
				.message(message)
				.build();
		return response.create();
	}
   // for error
	public static ResponseEntity<?> createErrorResponse(Object data, HttpStatus status) {

		GenericResponse response = GenericResponse.builder().
				responseStatus(status)
				.status("failed")
				.message("failed")
				.data(data).build();
		return response.create();
	}

 // agr msg or status send krne ka condition aye to ye send kr denge	
	public static ResponseEntity<?> createErrorResponseMessage(String message, HttpStatus status) {

		GenericResponse response = GenericResponse.builder()
				.responseStatus(status)
				.status("failed")
				.message(message)
				.build();
		return response.create();
		//every api end point  me use krenge isse fir globalexception class se jo response send kr rhe the usme bhi change controller handler ki tarah
	}

}
