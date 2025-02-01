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
 * (endpoint abstraction)pechle lecture me kuch bcha tah like controller class se hume @RequestBody or pathvaiable,Requestparam sab ko htana tha method ke parameter se 
 * (1)is lecture me  jo static value hota hai usi ko constant me define krte hai like noteendpoint me search method me defaultvalue="10" ye static h
 *    solution hum ek jagah constant me ye value le lenge fir waha call kr lenge like constants.property is way se bhi kr skte h
 *  --->  util packege me constant naam ka class bnaya tha usse hi use krenge
 *    (2) hasRole(ADMIN) static hai hr jagah use kr rhe hai to isse constant me define kr skte hai
 *    * */

}
