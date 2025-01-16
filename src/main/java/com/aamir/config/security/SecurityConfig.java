package com.aamir.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		//abhi password ko encord nhi kiya humara text formate me hi hai isliye NoOpPasswordEncoder.getInstance()
		//provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	
	
		//auth or home me koi authentication nhi chahiye
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(req -> req
				.requestMatchers("/api/v1/home/**", "/api/v1/auth/**").permitAll()
				//baki sab request pe authentication cha
				.anyRequest().authenticated())//dono url pe hit krke dekh authenticated hai abhi tak yahi SecurityFilterChain
		         //httpBasic use krenge api ke liye fir userdetais,userdetailsService bhi bnalenge
				.httpBasic(Customizer.withDefaults())
		    //jwtfilter se ane ke baad 
		        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		        //request kee baad kon sa jwtfilter call ho parmeter  (jwtFilter,konsa filter->UsernamePasswordAuthenticationFilter)
		        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
				
		return http.build();
	}

}
