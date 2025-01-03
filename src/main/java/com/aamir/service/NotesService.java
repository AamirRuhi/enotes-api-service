package com.aamir.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aamir.dto.NotesDto;

public interface NotesService {

	public boolean  saveNotes(String notes,MultipartFile file) throws Exception;
	
	public List<NotesDto> getAllNotes();
}
