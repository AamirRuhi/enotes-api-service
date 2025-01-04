package com.aamir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aamir.dto.NotesDto;
import com.aamir.dto.NotesResponse;
import com.aamir.entity.FileDetails;
import com.aamir.service.NotesService;
import com.aamir.util.CommonUtil;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {
	
@Autowired
private NotesService notesService;
	
@PostMapping("/")
public ResponseEntity<?> saveNotess(@RequestParam String notes,@RequestParam(required=false) MultipartFile file) throws Exception{
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
@GetMapping("/download/{id}")
public ResponseEntity<?> downloadFile(@PathVariable Integer id) throws Exception{
	
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

@GetMapping("/")
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
@GetMapping("/user-notes")
public ResponseEntity<?> getAllNotesssByUser(
		@RequestParam(name="pageNo",defaultValue = "0") Integer pageNo,
		@RequestParam(name="pageSize",defaultValue = "10") Integer pageSize	){
	
	Integer userId=1;
	NotesResponse allNotes = notesService.getAllNotesByUser(userId,pageNo,pageSize);
	/*
	 * if(CollectionUtils.isEmpty(allNotes)) { // data nhi aya to no content ka koi
	 * body nhi hota return ResponseEntity.noContent().build(); }
	 */
	//object pass krna hai isliye createBuildResponse
	return CommonUtil.createBuildResponse(allNotes, HttpStatus.OK);
	
}

}
