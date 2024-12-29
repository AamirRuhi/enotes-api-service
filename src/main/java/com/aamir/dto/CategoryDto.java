package com.aamir.dto;

import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	private Integer id;
	//@valid bhi lgana hai save-category ke handler pe 
	//2nd way se valida krte ha class bnake
  // @NotBlank(message="name should not blank ,null,empty")
   //@Min(value = 10)
   
	//@Max(value = 100)
	private String name;
//   @NotBlank(message="name description not blank ,null,empty")
//   @Min(value = 10)
//   @Max(value = 100)
	private String description;

	//@NotNull
	private Boolean isActive;

	private Integer createdBy;
	
	private Date createdOn;
	
	private Integer updatedBy;
	
	private Date updatedOn;

}
