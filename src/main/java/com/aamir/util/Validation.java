package com.aamir.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.aamir.dto.CategoryDto;
import com.aamir.dto.TodoDto;
import com.aamir.dto.TodoDto.StatusDto;
import com.aamir.dto.UserDto;
import com.aamir.entity.Role;
import com.aamir.enums.TodoStatus;
import com.aamir.exception.ExistDataException;
import com.aamir.exception.ResourceNotFoundException;
import com.aamir.exception.ValidationException;
import com.aamir.repository.RoleRepository;
import com.aamir.repository.UserRepository;

@Component
public class Validation {

	 @Autowired
	    private RoleRepository roleRepository;
	   
	 @Autowired
	 private UserRepository userRepository;
	 
	
	public void categryValidation(CategoryDto categoryDto) {
		Map<String , Object> error=new LinkedHashMap<>();
		
		if(ObjectUtils.isEmpty(categoryDto)) {
		//	koi fully blank diya json me to ye excetion ayega 
			throw new IllegalArgumentException("category object/json should'nt be null or empty");
		}else {
			// check all field  , validation on name
			if(ObjectUtils.isEmpty(categoryDto.getName())) {
				error.put("name", "name field is empty or null");
			}else {
				//if user gave name then we will check maxsize..etc
				if(categoryDto.getName().length()<3)
				{
					error.put("name", "name length min 3");
				}
				if(categoryDto.getName().length()>100)
				{
					error.put("name", "name length max 100");
				}
			}
			
			//validation on description
			if(ObjectUtils.isEmpty(categoryDto.getDescription())) {
				error.put("description", "description field is empty or null");
			}
				
			//validat on isActive
					if(ObjectUtils.isEmpty(categoryDto.getIsActive())) {
						error.put("isActive", "isActive field is empty or null");
					}else {
						//if user gave isActive then we will check maxsize..etc
						if(categoryDto.getIsActive()!=Boolean.TRUE.booleanValue() && categoryDto.getIsActive()!=Boolean.FALSE.booleanValue())
						{
							error.put("isActive", " invalid value isActive field ");
						}
						
					}		
	         	}
		if(!error.isEmpty())
		{
			//agr error hai uske liye ValidationException banaya
			throw new ValidationException(error);

		}
		
	}
	// call krna h todoserviceimpl se
	public void todoValidation(TodoDto todo) throws Exception {
		//status ko validate krna hai
		StatusDto reqStatus = todo.getStatus();
		//TodoStatus[] status = TodoStatus.values(); direct kr lenge niche
		Boolean statusFound=false;
		for(TodoStatus st: TodoStatus.values()) {
			if(st.getId().equals(reqStatus.getId())) {
				statusFound=true;
			}
		}
		if(!statusFound)
		{
			throw new ResourceNotFoundException("invalid status ");
		}
	}
	//for register user
	public void userValidation(UserDto userDto) {
	
		//isEmpty deprecated ho gya hai isliye hasText le liya
		if(!StringUtils.hasText(userDto.getFirstName())) {
			throw new IllegalArgumentException("first name is invalid");
		}
		
		if(!StringUtils.hasText(userDto.getLastName())) {
			throw new IllegalArgumentException("last name is invalid");
		}
		
		if(!StringUtils.hasText(userDto.getEmail()) || !userDto.getEmail().matches(Constants.EMAIL_REGEX)) {
			//regex ke help se email ko validate krenge like @ symble h ,gmail h.  in util pakege me Constant class bnalenge usme ,matches method h
			throw new IllegalArgumentException("emails is invalid");
		}else {
			//validate email  already existing
			Boolean existEmail =userRepository.existsByEmail(userDto.getEmail());
			if(existEmail) {
				throw new ExistDataException("this email already exist");
			}
			
		}
		if(!StringUtils.hasText(userDto.getMobNo()) || !userDto.getMobNo().matches(Constants.MOBNO_REGEX)) {
			//regex ke help se mobno ko validate krenge like .  in util pakege me Constant class bnalenge usme ,matches method h
			throw new IllegalArgumentException("mobil no is invalid");
		}
        if (!StringUtils.hasText(userDto.getPassword()) || !userDto.getPassword().matches(Constants.PASSWORD_REGEX)) {
            throw new IllegalArgumentException(
                "Password is invalid. It must be at least 8 characters long, include at least one uppercase letter, one lowercase letter, one digit, and one special character."
            );
        }
		//roles pe validation krna hai request me send krna hai , sare roles ka id get kr lunga 
		if(CollectionUtils.isEmpty(userDto.getRoles()))
		{
			throw new IllegalArgumentException("role is  invalid");
		}else {
			List<Integer> rolesIds = roleRepository.findAll().stream().map(r->r.getId()).toList();
			//request ka id or db ka id match nhi hua to exxception throw kr lenge
			
			List<Integer> invalidreqRolesids = userDto.getRoles().stream()
			.map(r->r.getId()).filter(roleId->!rolesIds.contains(roleId)).toList();
			
                   if(!CollectionUtils.isEmpty(invalidreqRolesids)) {
           			throw new IllegalArgumentException("role is  invalid"+ invalidreqRolesids);

                   }
		}
	}
}
