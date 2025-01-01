package com.aamir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aamir.dto.NotesDto;
import com.aamir.service.NotesService;
import com.aamir.util.CommonUtil;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {
	
@Autowired
private NotesService notesService;
	
@PostMapping("/")
public ResponseEntity<?> saveNotess(@RequestBody NotesDto notesDto) throws Exception{
	//category bhi dena hai like "categoryDto":{id:12
//}
	boolean saveNotes = notesService.saveNotes(notesDto);
	if(saveNotes) {
		//generic response denge ,data nhi dena h msg dena h
		return CommonUtil.createBuildResponseMessage("notes saved success", HttpStatus.CREATED);
	}
	return CommonUtil.createErrorResponseMessage("notes not saved", HttpStatus.INTERNAL_SERVER_ERROR);
	
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

}
