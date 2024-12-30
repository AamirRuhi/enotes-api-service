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

	
	/*Auditing is the process of tracking changes to data within a system to maintain a history of operations such as 
	 * creation, modification, and deletion. In the context of software applications, auditing is implemented to monitor 
	 * and log details about who performed an action, what was changed, when it occurred, and sometimes why it happened.
	 * Example: Tracking who updated the name of a category in an e-commerce platform
	 * here createdby,createdon,updatedby,updatedon apne aap create update...hoga by auditing
    auding concept apply on master data only
    jab create kru to createdby createdon me update ho jb update kre to updatedby, updatedon me update ho 
*/
}
