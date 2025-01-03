package com.aamir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAware")
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
}  notes string me mila h ,pr save krna h notes object me by objectmapper ke help se
ab form-data me send krenge key notes ,value- wo pura json (required=false) liya @requestparam se pehle qki abhi hum file upload nhi kr rhe hai
// ab file bhi upload krna hai to fileDetails ke naam se ek entity bna lunga

*/ 

}
