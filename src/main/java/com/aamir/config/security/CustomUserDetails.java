package com.aamir.config.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.aamir.entity.User;

public class CustomUserDetails implements UserDetails{
	
	private User user;
	
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
			List<SimpleGrantedAuthority> authority=new ArrayList<>();
			user.getRoles().forEach(r->{
				authority.add(new SimpleGrantedAuthority("ROLE_"+r.getName())); //role lena hai like ROLE_ADMIN,agr ROLE liya to ROLE_ lena hai
			});  //ager db me ROLE_ADMIN ,ROLE_USER hota to ROLE_ ( key) nhi dena padta jaise upper diya hai
		return authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
