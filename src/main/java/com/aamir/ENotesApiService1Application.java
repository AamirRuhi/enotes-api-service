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
	 * for save todo
    {
    "title":"java core",
    "status":{
        "id":1
    }
}http://localhost:8081/api/v1/todo/1 id ke sath naam nhi aa rha hai naam lane ke liye
//in progress me hai to emil send krenge plz complete it 12pm pe automatic TO USER CHAIYE TO USER MODULE KRLO
}  
todo management ---> set task status Todo entity bnao
status ko manage krne ke liye enums pakage me TodoEnums class bnayege
definition : Enums are used for representing a fixed set of related constants in a structured and meaningful way. 
1->Status Codes (e.g., ACTIVE, INACTIVE, PENDING)
2->Days of the Week (e.g., MONDAY, TUESDAY)
3->Directions (e.g., NORTH, SOUTH, EAST, WEST)
4->Payment Methods (e.g., CREDIT_CARD, PAYPAL, CASH)
5->Order States (e.g., PLACED, SHIPPED, DELIVERED, CANCELLED)

*/ 

}
