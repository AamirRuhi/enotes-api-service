package com.aamir.dto;

import com.aamir.dto.NotesDto.CategoryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesRequest {
	private String title;
	private String description;
	// category ka object dena padega
	private CategoryDto category;

}
