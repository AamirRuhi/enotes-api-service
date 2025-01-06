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
    "title":"python with boot",
    "description":"python is programming language",
    "category":{
        "id":58
    }
}  
hum direct delete nhi krenge recyle bean me dalenge or time set kr denge ki kab delete hoga ,with time ke andar hum usse resume bhi kr skte h
//krne ke liye Notes entity me field define krenge isDeleted default false hoga agr user delete kr dega to true user ko nhi dikhega
 * deletedOn kab delete hua

*/ 

}
