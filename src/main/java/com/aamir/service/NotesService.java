package com.aamir.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aamir.dto.NotesDto;
import com.aamir.dto.NotesResponse;
import com.aamir.entity.FileDetails;

public interface NotesService {

	public boolean  saveNotes(String notes,MultipartFile file) throws Exception;
	
	public List<NotesDto> getAllNotes();

	public byte[] downloadFile(FileDetails fileDetails) throws Exception;

	public FileDetails getFileDetails(Integer id) throws Exception;

	public NotesResponse getAllNotesByUser(Integer userId, Integer pageNo, Integer pageSize);

	//public NotesResponse getAllNotesByUser(Integer userId); baade add , Integer pageNo, Integer pageSize dynamic
}
