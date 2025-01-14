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
	
/*(
 * isme search notes krenge custom query use krenge or ( 1 interface hai jpa me Specification custom query ke liye abhi ye nhi use krenge)
 * title description category inhi 3no se search hoke ane wala hai ,notesRepository me custom query likhenge jo note add kiya h usi ko dikhega
 *  created_by,is_Deleted false ho jiska
 *  (1)notesrepome me query likha searchNotes
 *  http://localhost:8081/api/v1/notes/search?key=java   pageNo ,pageSize default le rha hai agr match nhi kiya title ,description,category se to null
 *  
 *  
 *  
  * */

}
