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
 * If the project is simple with minimal configurations, application.properties might be easier. but if we have Complex configurations with nested properties.
   then we use aaplication.yml ,it Manage configurations for multiple environments (dev, test, prod) is simplified using profiles.
(1) first we do it with application.properties file , every environments ke liye properties file bna liya like application-dev.properties...etc
run krke dekhnge ki konsa profile chal rha hai to default wala chal rh h console EX:-No active profile set, falling back to 1 default profile: "default"
application.properties defaut wala comment krlo or usme spring.profiles.active=dev to dev environment active hoga,prod to prod active hoga
(2) database 4ro environmemt ka create kariye with same table name without data ,like enotes_dev,enotes_test,enotes_prod,enotes_uat


, then we will do it by using application.yml in next video

 
 *  
 *  
  * */

}
