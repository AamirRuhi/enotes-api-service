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
 *  
 *  
 *  
 *  
 *  
 * jo token mila hai ab jab hum api access karenge to header me token pass krna hai,jo token header me mil rha hai usse validate krna hai ,sahi token h ki nhi
 * ,expire hogya h token ya nhi hua hai use check krebge ,role check krenge 
 * 
 * role based authentication like user ko konsa api access kr skta hai ,admin konsa, or dono konsa api access kr skta hai
 *  
 *  
 *  */

}
