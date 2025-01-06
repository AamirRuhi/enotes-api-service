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
favorite note add or remove favorite notes 1->favoriteNotes entity bnalo
pehle add favove note , getall faveurite notes and unfaveurite notes give favouritenotes id 
*/ 

}
