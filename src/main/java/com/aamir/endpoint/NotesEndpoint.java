package com.aamir.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//constant ko import krenge,ab ROLE_ADMIN direct use kr skte hai
import static com.aamir.util.Constants.ROLE_ADMIN;
import static com.aamir.util.Constants.ROLE_USER;
import static com.aamir.util.Constants.ROLE_ADMIN_USER;
import static com.aamir.util.Constants.DEFAULT_PAGE_NO;
import static com.aamir.util.Constants.DEFAULT_PAGE_SIZE;

import org.springframework.web.multipart.MultipartFile;
@RequestMapping("/api/v1/notes")
public interface NotesEndpoint {
	@PostMapping("/")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> saveNotess(@RequestParam String notes,@RequestParam(required=false) MultipartFile file) throws Exception;
	
	
	// file ko download krne ke liye file ke unique id se 
	@GetMapping("/download/{id}")
	@PreAuthorize(ROLE_ADMIN_USER)
	public ResponseEntity<?> downloadFile(@PathVariable Integer id) throws Exception;
	
	
	@GetMapping("/")
	@PreAuthorize(ROLE_ADMIN)
	public ResponseEntity<?> getAllNotesss();
	
	@GetMapping("/user-notes")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> getAllNotesssByUser(
			@RequestParam(name="pageNo",defaultValue = DEFAULT_PAGE_NO) Integer pageNo,
			@RequestParam(name="pageSize",defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize	);
	
	@GetMapping("/delete/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> deleteNotes(@PathVariable Integer id) throws Exception;
	
	
	@GetMapping("/restore/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> restoreNotes(@PathVariable Integer id) throws Exception;
	
	@GetMapping("/recycle-bin")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> getUserRecleBinNotes() throws Exception;
	
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> hardDeleteNotes(@PathVariable Integer id) throws Exception;
	
	@DeleteMapping("/delete")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> emptyUserRecycleBine()throws Exception;
	
	@GetMapping("/fav/{noteId}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> favouriteNotes(@PathVariable Integer noteId )throws Exception;
	
	@DeleteMapping("/un-fav/{favNoteId}")
	public ResponseEntity<?> unFavouriteNotes(@PathVariable Integer favNoteId)throws Exception;
	
	
	@GetMapping("/fav-note")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> getUserFavouritNotes()throws Exception;
	
	@GetMapping("/copy/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> copyNotes(@PathVariable Integer id )throws Exception;
	
	@GetMapping("/search")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> searchNotes(@RequestParam (name="key",defaultValue = "") String  key ,
			@RequestParam(name="pageNo",defaultValue = DEFAULT_PAGE_NO) Integer pageNo,
			@RequestParam(name="pageSize",defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize	);
	
}
