package com.aamir.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aamir.dto.FavouriteNotesDto;
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
	
	public void softDeleteNotes(Integer id) throws Exception;

	public void restoreNotes(Integer id) throws Exception;

	public List<NotesDto> getUserRecyclyBinNotes(Integer userId);

	public void hardDeleteNotes(Integer id) throws Exception;

	public void emptyRecycleBin(Integer userId);
	// yahi bnalo favouritenotes ke liye bhi
	
	public void favouriteNotes(Integer noteId) throws Exception;
	
	public  void unFavouriteNotes(Integer favouriteNotesId) throws Exception;
	
	public List<FavouriteNotesDto> getUserFavouriteNotes() throws Exception; 
	//FavouriteNotesDto bnalo 
}
