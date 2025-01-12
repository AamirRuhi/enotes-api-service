package com.aamir.dto;

import java.util.List;



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
public class UserResponse {
	
private Integer id;
private String firstName;
private String lastName;
private String email;
//password response me nhi denge status dende
private StatusDto status;
private String mobNo;
private List<RoleDto> roles;



@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public static class RoleDto{
	private Integer id;
	private String name;

}

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public static class StatusDto{
	private Integer id;
	private boolean isActive; 
	
}

}
