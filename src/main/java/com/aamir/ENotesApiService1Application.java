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
	 * for register role table me jake id and name bnalenge 1, 2 -->USER , ADMIN 
	 * 
    
   {
    "firstName":"Becoder",
    "lastName":"demo",
    "email":"demo@gmail.com",
    "mobNo":"7203180602",
    "password":"Demo@123",
    "roles":[
        {
        "id": 1
        },
      {
        "id":2
      }
    ]
}
//setRole(userDto,user); method nhi use kiye to register time pe niche wala exception mila
 *  {
    "status": "failed",
    "message": "failed",
    "data": "JSON parse error: Cannot deserialize value of type `java.util.ArrayList<com.aamir.dto.UserDto$RoleDto>` from Object value (token `JsonToken.START_OBJECT`)"
 means-->The RoleDto property in your UserDto is defined as a List<RoleDto> but the JSON you are passing contains 
 a single object instead of an array.
    }

*/ 

}
