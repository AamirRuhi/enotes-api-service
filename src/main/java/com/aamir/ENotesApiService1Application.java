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
 * Log Levels---
 *  STRACE – Fine-grained, typically only useful in development.
   DEBUG – Debugging information.
   INFO – General informational messages.
   WARN – Warnings about potential issues.
   ERROR – Error events.

 Logging  offer a systematic way to record, monitor, and analyze the behavior of an application.like
Debugging and Troubleshooting

SLF4J (Simple Logging Facade for Java) is a logging framework abstraction that provides a standardized API for various 
logging frameworks in Java. Instead of directly using a specific logging library like Log4j or java.util.logging, 
 *  @SLF4J lombok ke andar milta hai abhi 1 class ke andar homecontroller hi implement krnege
 *  
 *  
 *  logback.xml bnake code copy past kiya to log folder bnega
  * */

}
