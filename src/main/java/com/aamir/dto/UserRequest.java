package com.aamir.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
	
private Integer id;
private String firstName;
private String lastName;
private String email;
private String password;
private String mobNo;
private List<RoleDto> roles;
//RoleDto inner class bnalenge


@Builder
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public static class RoleDto{
	private Integer id;
	private String name;

}

}
