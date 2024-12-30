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

	
	/* Generic response response me only 3 field denge 1-status,2-message,3-data me data send kren wale hai
	 * abhi hum save kr rhe hai to response save success a rha h lekin hum generic type me dena hai
	 * Using a generic response in APIs is a common practice to standardize the structure of responses,
	 *  making it easier for clients to parse and handle them.
	 *  ex like status, error messages, or timestamps.
	 *  handler pakage class generic response
*/ 

}
