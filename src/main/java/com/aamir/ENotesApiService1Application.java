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
 * first HomeController ke liye krte hai
 controller ke endpoint ko ko secure krne wale h pakge endpont ka endpoint ke andr interface bnayenge sab controller ke liye jisme abstract method
 defined krenge only or controller se endpoint ko remove kr lenge fir implement HomecontrollerEndpont fir @override
   * 
   * */

}
