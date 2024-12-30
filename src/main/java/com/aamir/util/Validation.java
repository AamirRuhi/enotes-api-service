package com.aamir.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.aamir.dto.CategoryDto;
import com.aamir.exception.ValidationException;

@Component
public class Validation {

	
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
}
