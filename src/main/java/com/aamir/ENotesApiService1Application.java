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

	
	/* {
	 * {
    "firstName":"khan",
    "lastName":"demoo",
    "email":"a4aamir03@gmail.com",
    "mobNo":"8203180602",
    "password":"Deymo@123",
    "roles":[
        {
        "id": 1
        }
     
    ]
}
	 
	 * 
	 **email already exist ke liye only Validation class me else me custom method bnana hai bas 
	 *send email to user while registering for registeration is conformed add dependency spring boot starer mail,serviceimpl pakage me EmailService naam ka class bnao
    EmailRequest class bnayenge dto me email me kya lena hai
   

*/ 

}
