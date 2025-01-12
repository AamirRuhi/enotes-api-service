package com.aamir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAware")
@EnableScheduling
public class ENotesApiService1Application {

	public static void main(String[] args) {
		SpringApplication.run(ENotesApiService1Application.class, args);
	}
	
/*
 * 
 *  
 *  
  * bnayenge ,UserDto ki rename krenge UserRequest ,UserResponse bnayenge
  * dto tab bnate h jab request and response same hota hai,alag ho to request and response
 *   //jo user loggin hai uska information kaise get krna hai   , logged in user ka info get krne ke liye ,util pakage ke commonUtil me ek method
getLoggedInUser
note: jo AuditAwareConfig me static user ko diya hai wo dynamic krna hai 
ab notesServiceimpl me userId dynamic diya ,todoServiceimpl jaha integer userId=2 tha usse dtnamic kiya
 *  */

}
