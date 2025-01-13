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
	
/*(1)send mail-password reset link*(2)verification reset link ,(3) set new password
 *AccountStatus entity me hi use krenge buhut logic h isme eg:user ko block krna ,password reset krna, 3time user ne passwod wron kiya to block krna
 *  AccountStatus 1 field add krenge passwordResetToken registration time ye null store krega,login se pehle password reset hota hai,after registration
 *  AccountStatus me column passwordResetToken bhi bna lenge
 *  http://localhost:8081/api/v1/home/send-email-reset?email=a4aamir03@gmail.com before login for registered user
 *  Change my password pr click krte hi link mila id or code ab usee verify krna ahi
 *  
 *  
  * */

}
