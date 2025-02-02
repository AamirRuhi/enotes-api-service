package com.aamir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAware")
@EnableScheduling
public class ENotesApiService1Application {

	public static void main(String[] args) {
		SpringApplication.run(ENotesApiService1Application.class, args);
	}
	
/*(swagger) ek type ka postman ke jaisa hai ye ui provide krta hai ,frontend walo ko help krta hai api integration krne me ,backend me api test krne
 * adddependncy Springdoc openApi Starter WebMVC UI add krke run kr lenge fir hit on google http://localhost:8080/swagger-ui/index.html to username or password
 * qki isko authenticate nhi kiya hai to isse authenticate krenge SecurityCofig me jake swagger-ui ke baad koi request aye use authenticate krna hai,docs ko bhi
 * "/swagger-ui/**","/v3/api-docs/**" is tarike se ab ye dono without authen ke access hoga ab run kiya to username or password nhi managa
 * 
 * (2)User-controller-->tags hota hai use kaise customize krenge ,authEndpoint me @tags anno se @Tag(name = "User Authetication", description ="All the user Authentication Apis")
 (3)endpint level pe bhi cosmize krenge 	@Operation(summary = "User register endpoint",tags={"Auth"}),Auth naam ka folder check krega nhi mila to create kr diya
(4)@get,@post,@put.@delete ko order me krne ke liye apllication.yml me springdoc:swagger-ui:operationsSorter:method
(5)tags ko sort ke liye tagsSorter:alpha (5)url path change krne ke liye (yml)path: /enotes-doc securityconfig se access dena hai ab enotes-doc likenge to swagger-ui me change ho jayega
(7)document ko change ke liye /enotes-api-doc(yml) then securityconfig me bhi(8)jwt token ke liye ek class bnayege SwaggerConfig
 *  (8)dto me notesRequest bnayenge qki qki notesdto ka sara object mil rha h createdby or updatedby bhi ye htana hai notes save krte time
 *  (9) agr koi register kre to 200 or error aye to bhi kuch krenge authendpoint me @ApiResponses(value="....)201,500,400
 *  
 *  
 *  
 *  *    * */

}
