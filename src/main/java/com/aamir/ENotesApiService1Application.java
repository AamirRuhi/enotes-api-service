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
	
/*user login hone ke baad password change kr skta hai
 * usr se kya lene wala hu to passwordChangeRequest dto me bnayenge oldpasswod , newPassword liya
 * rename UserService---->AuthService, UserServiceImpl ---->AuthServiceImpl
 * 
 * ab UserService bnayenge loggin ke baad  usme user specific methods honge,UserServiceImpl 
 * 
 * body me {
    "oldPassword":"Aamir@123",
    "newPassword":"Mamir@123"
} Authorization me token or login response me jo password aa rha tha wo bhi remove kiya
 *  
  * 
  * 
  * */

}
