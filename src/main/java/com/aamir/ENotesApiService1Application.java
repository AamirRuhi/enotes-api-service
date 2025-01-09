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
}// spring security appy from here add security dependency add krte hi console pe password generate hoga abhi koi bhi api url hit krte h to 401 unthorize	 
abhi depency lgate hi krna h to Authorization-Basic Auth username->user,password->console wala password	 tab get kr payenge  ,auth,home without authentication get hoga 
//securityconfig,userdetails,userdetailservice likhne ke pad console pr password generate nhi hoga
 *ab get krenge category ko bhi access kr skte hby email an password Authorization->Basic Auth,username->email,password->password db wala,all category get kr pa rhe h	 
note:hume hr baar email,password dena pad rha h access ke kiye ko jipe humne authentication lgaya hah,ab ek login api bnate h or authenticate krwate h , fir wo sab ko access kre
 loken se hi authcontroller me ,overall http://localhost:8081/api/v1/auth/login body me email->db,password-->password db wala (login ko rha hai
*
*/ 

}
