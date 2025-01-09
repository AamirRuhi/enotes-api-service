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
	 * 
    "{
    "email":"a4aamir03@gmail.com",
    "password":"Deymo@123"
}   } Authorization me noAuth
     
    ]
}/isme hum custom token generate krenge,jo api access krenge authorization me token denge,add dependecy jjw imp,jjw jaction,jjw api 
jwtservice,jwtserviceimpl bnayege
note -->login time token generate krenge fir usi token ke help se other api bhi access kr skte hai
*/ 

}
