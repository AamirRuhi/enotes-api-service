package com.aamir.service;

import java.util.List;

import com.aamir.dto.NotesDto;

public interface NotesService {

	public boolean  saveNotes(NotesDto notesDto) throws Exception;
	
	public List<NotesDto> getAllNotes();
}
