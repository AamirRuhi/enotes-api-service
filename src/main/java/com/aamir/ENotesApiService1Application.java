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
}aacount verification ke liye jo mail gya h user ke pass
register time pe user ka account bydefault inActive rhne wala hai ,to entity  me 1 class AccountStatus jisme
 2 field add krenge isActive and varificationCode,table bnayenge AccountStatus liye
if user inActive h to verificationCode pe click krke Active ho jayega ,
	 
	 
	 

*/ 

}
