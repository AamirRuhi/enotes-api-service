package com.aamir.dto;

import java.util.List;

import com.aamir.entity.Role;
import com.aamir.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
private Integer id;
private String firstName;
private String lastName;
private String email;
private String password;
private String mobNo;
private List<RoleDto> roles;
//RoleDto inner class bnalenge


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public static class RoleDto{
	private Integer id;
	private String name;

}

}
