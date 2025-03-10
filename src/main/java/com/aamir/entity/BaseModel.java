package com.aamir.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
//	private Boolean isActive;
//	private Boolean isDeleted; 
	//abstract class ka object nhi bna skte
    
	@CreatedBy
	@Column(updatable = false)
	private Integer createdBy;
	
	@CreatedDate
	@Column(updatable = false)
	private Date createdOn;
	
	@LastModifiedBy
	@Column(insertable  = false)
	private Integer updatedBy;
	
	
	@LastModifiedDate
	@Column(insertable  = false)
	private Date updatedOn;
	//4ro field extend krna hai sab me or mmain class me @EnablejpaAuditing 

}
