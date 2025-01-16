package com.aamir.dto;

import java.util.Date;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// crt +shift+o remove to unwanted import
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
	private Integer id;

	private String title;

	//innser class bnayenge
	private StatusDto status;

	private Integer createdBy;

	private Date createdOn;

	private Integer updatedBy;

	private Date updatedOn;
	
	@Builder
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class StatusDto{
		//response me ese hi status denge
		private Integer id;
		private String name;
	}

}
