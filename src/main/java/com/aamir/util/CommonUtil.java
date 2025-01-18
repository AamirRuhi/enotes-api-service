package com.aamir.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aamir.config.security.CustomUserDetails;
import com.aamir.entity.User;
import com.aamir.handler.GenericResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

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

	public static String getContentType(String originalFileName) {
		//FilenameUtils se extension get kiya sab file ka 
		String extension = FilenameUtils.getExtension(originalFileName);
		switch (extension) {
		case "pdf":
			return "application/pdf";
		case "xlsx":
			return "application/vnd.openxmlformats-officedocument.spreadsheettml.sheet";
		case "txt":
			return "text/plan";
		case "png":
			return "image/png";
		case "jpeg":
			return "image/jpeg";
		default:
			return "application/octet-stream";
		}
	}

	public static String getUrl(HttpServletRequest request) {
		//url get krenge
		String apiUrl = request.getRequestURL().toString();////http://localhost:8081/api/v1/auth/
		//lekin hume port tk get krna hai replace concept lgayenge
		apiUrl=apiUrl.replace(request.getServletPath(), "");////http://localhost:8081
		return apiUrl; //http://localhost:8081/api/v1/auth/ lekin hume port tk get krna hai
	}

	public static User getLoggedInUser() {
		try {
			//return type UserResponse abhi nhi kiye warna hume mapper ke help se User me to top me autowired krna hoga @component bnana hoga 
			//	hum isse component nhi bnana chahte islite return type User liya ab UserController banyege
				CustomUserDetails	logUser=(CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				return logUser.getUser();
		} catch (Exception e) {
			throw e;
		}
		
	}
}
