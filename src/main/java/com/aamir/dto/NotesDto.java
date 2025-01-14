package com.aamir.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.aamir.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesDto {
	private Integer id;
	private String title;
	private String description;
	// category ka object dena padega
	private CategoryDto category;
	
// uper ke 3 request krne wale hai baki auditing he help se set hoga
	private Integer createdBy;
	
	private Date createdOn;
	
	private Integer updatedBy;
	
	private Date updatedOn;
	
	private FilesDto fileDetails;
	
	private boolean isDeleted;
	private LocalDateTime deletedOn;


	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FilesDto{
		private Integer id;
		private String originalFileName;
		private String displayFileName;
       //otput me yahi dono dikhana hai
		

		
	}
// last me dekhenge category ke array me category ka sab object aa rha hai lekin hume only id,name dikhana hai  innser class ka use krenge
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CategoryDto{
		private Integer id;
		private String name;
		
	}
}
