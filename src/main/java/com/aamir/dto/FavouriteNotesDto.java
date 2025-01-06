package com.aamir.dto;

import com.aamir.entity.Notes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouriteNotesDto {
	
	
	private Integer id;
private NotesDto note;
private Integer userId;

}
