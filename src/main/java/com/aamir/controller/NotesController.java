package com.aamir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aamir.dto.FavouriteNotesDto;
import com.aamir.dto.NotesDto;
import com.aamir.dto.NotesResponse;
import com.aamir.endpoint.NotesEndpoint;
import com.aamir.entity.FileDetails;
import com.aamir.entity.User;
import com.aamir.service.NotesService;
import com.aamir.util.CommonUtil;

@RestController

public class NotesController implements NotesEndpoint{
	
@Autowired
private NotesService notesService;
	
@Override
public ResponseEntity<?> saveNotess( String notes, MultipartFile file) throws Exception{
	//category bhi dena hai like "categoryDto":{id:12
//} file upload me data ko string me file ko muiltipart me 
	//abhi file nhi liye h to (required=false) krdenge @RequestParam ke baad or form-data me key->notes ,value- json
	boolean saveNotes = notesService.saveNotes(notes,file);
	if(saveNotes) {
		//generic response denge ,data nhi dena h msg dena h
		return CommonUtil.createBuildResponseMessage("notes saved success", HttpStatus.CREATED);
	}
	return CommonUtil.createErrorResponseMessage("notes not saved", HttpStatus.INTERNAL_SERVER_ERROR);
	
}

// file ko download krne ke liye file ke unique id se 
@Override
public ResponseEntity<?> downloadFile( Integer id) throws Exception{
	
	FileDetails fileDetails=notesService.getFileDetails(id);
	byte[] data=notesService.downloadFile(fileDetails);
	//responseentity me sab set krna hai
	HttpHeaders headers =new HttpHeaders();
	//getContentType methos commonutils me bnaya hai
	String contentType = CommonUtil.getContentType(fileDetails.getOriginalFileName());
	headers.setContentType(MediaType.parseMediaType(contentType));
	headers.setContentDispositionFormData("attachment", fileDetails.getOriginalFileName());
	//return new ResponseEntity<>(" ",HttpStatus.OK);body me pass krna hai
	
	return ResponseEntity.ok().headers(headers).body(data);
	
}
@Override
public ResponseEntity<?> getAllNotesss(){
	List<NotesDto> allNotes = notesService.getAllNotes();
	if(CollectionUtils.isEmpty(allNotes)) {
		// data nhi aya to no content ka koi body nhi hota
	return  ResponseEntity.noContent().build();
	}
	//object pass krna hai isliye createBuildResponse
	return CommonUtil.createBuildResponse(allNotes, HttpStatus.OK);
	
}
// jo user add notes kiya hai wahi get kr skta hai notes , abhi static user de rha hai
//dynamic dikhane ke liye @requestparam but pehle getAllNotesssByUser parameter me kuch nhi tha,getAllNotesByUser pageno,pagesize pass kr denge ,service or imple me bhi
//default 0 index se strt hoga ,custom krne ke liye param me key pagen0->1,pagesize->4
@Override
public ResponseEntity<?> getAllNotesssByUser( Integer pageNo,Integer pageSize	){
 //jo user loggin h ab uski id dynamic krenge
//	Integer userId=1;
	Integer userId = CommonUtil.getLoggedInUser().getId();
	NotesResponse allNotes = notesService.getAllNotesByUser(pageNo,pageSize);
	/*
	 * if(CollectionUtils.isEmpty(allNotes)) { // data nhi aya to no content ka koi
	 * body nhi hota return ResponseEntity.noContent().build(); }
	 */
	//object pass krna hai isliye createBuildResponse
	return CommonUtil.createBuildResponse(allNotes, HttpStatus.OK);
	
}
@Override
public ResponseEntity<?> deleteNotes( Integer id) throws Exception{
	notesService.softDeleteNotes(id);
	
	return CommonUtil.createBuildResponseMessage(" notes deleted success", HttpStatus.OK);
}

@Override
public ResponseEntity<?> restoreNotes( Integer id) throws Exception{
	notesService.restoreNotes(id);
	
	return CommonUtil.createBuildResponseMessage(" notes restored success", HttpStatus.OK);
}

@Override
public ResponseEntity<?> getUserRecleBinNotes() throws Exception{
	//deleted notes ko recycle bean me rakhne wale hai , authentication time user se is lunga lekin abhi static leke bna rha hu
	
	List<NotesDto> notes=notesService.getUserRecyclyBinNotes();
	if(CollectionUtils.isEmpty(notes)) {
		return CommonUtil.createBuildResponseMessage("Notes not availble in Recycle bin", HttpStatus.OK);
	}
	return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
}
//NotesSchedular completion  ke baad hum database se hard delete krne wale hai 
//ab ek api jise jo notes recycle bin me h use delete kr skta hai based on id , ya fully folder ko bhi delete kr skta hai
@Override
public ResponseEntity<?> hardDeleteNotes( Integer id) throws Exception{
	
	notesService.hardDeleteNotes(id);
	
	return CommonUtil.createBuildResponseMessage(" notes deleted success", HttpStatus.OK);
}
//ab recycle bin ka sara delete krne ke liye 
@Override
public ResponseEntity<?> emptyUserRecycleBine()throws Exception{
	//jo user login h , kis user ka notes recycle bin me h get get krke delete all , userid ststic lenege
	
	
	notesService.emptyRecycleBin();
	
	return CommonUtil.createBuildResponseMessage(" notes deleted success", HttpStatus.OK);
}


@Override
public ResponseEntity<?> favouriteNotes( Integer noteId )throws Exception{
	notesService.favouriteNotes(noteId);
	
	return CommonUtil.createBuildResponseMessage(" notes added favourite success", HttpStatus.CREATED);
}
@Override
public ResponseEntity<?> unFavouriteNotes( Integer favNoteId)throws Exception{
	
	notesService.unFavouriteNotes(favNoteId);
	
	return CommonUtil.createBuildResponseMessage(" remove  favourite notes success", HttpStatus.OK);
}
@Override
public ResponseEntity<?> getUserFavouritNotes()throws Exception{

	List<FavouriteNotesDto> userFavouriteNotes = notesService.getUserFavouriteNotes();
	if(CollectionUtils.isEmpty(userFavouriteNotes)) {
		return ResponseEntity.noContent().build();
	}
	
	return CommonUtil.createBuildResponse(userFavouriteNotes, HttpStatus.OK);
}
// notes ke id ke base pe copy krke fir se insert kr denge
//http://localhost:8081/api/v1/notes/copy/44
@Override
public ResponseEntity<?> copyNotes( Integer id )throws Exception{
	boolean copyNotes = notesService.copyNotes(id);
	if(copyNotes) {
		return CommonUtil.createBuildResponseMessage(" Copied  success", HttpStatus.CREATED);

	}
	return CommonUtil.createErrorResponseMessage(" Copied  failled try again", HttpStatus.INTERNAL_SERVER_ERROR);
}

@Override
public ResponseEntity<?> searchNotes(String  key ,Integer pageNo, Integer pageSize	){
 //jo user loggin h ab uski id dynamic krenge
//	Integer userId=1;
	Integer userId = CommonUtil.getLoggedInUser().getId();
	NotesResponse allNotes = notesService.getNotesByUserSearch(pageNo,pageSize,key);
	/*
	 * if(CollectionUtils.isEmpty(allNotes)) { // data nhi aya to no content ka koi
	 * body nhi hota return ResponseEntity.noContent().build(); }
	 */
	//object pass krna hai isliye createBuildResponse
	return CommonUtil.createBuildResponse(allNotes, HttpStatus.OK);
	
}

}
