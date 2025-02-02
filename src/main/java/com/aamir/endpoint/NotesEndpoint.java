package com.aamir.endpoint;

import static com.aamir.util.Constants.DEFAULT_PAGE_NO;
import static com.aamir.util.Constants.DEFAULT_PAGE_SIZE;
//constant ko import krenge,ab ROLE_ADMIN direct use kr skte hai
import static com.aamir.util.Constants.ROLE_ADMIN;
import static com.aamir.util.Constants.ROLE_ADMIN_USER;
import static com.aamir.util.Constants.ROLE_USER;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aamir.dto.NotesRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Notes", description ="All the Notes  Apis")
@RequestMapping("/api/v1/notes")
public interface NotesEndpoint {
	
	@Operation(summary = "Save Notess ",tags= {"Notes","User"},description = "User save notes")
	@PostMapping(value="/",consumes = "multipart/form-data")//swagr se notessave nhi kr pa rhe isliye value="/",consumes = "multipart/form-data"
	@PreAuthorize(ROLE_ADMIN_USER)//swagger se json dene ke liye @parameter use kiya NotesRequest bnaya only title des,cate req me lene ke liye
	public ResponseEntity<?> saveNotess(@RequestParam
			@Parameter(description = "Json String Notes",required = true,content = @Content(schema = @Schema(implementation = NotesRequest.class)))
			String notes,@RequestParam(required=false) MultipartFile file) throws Exception;
	
	
	// file ko download krne ke liye file ke unique id se 
	@Operation(summary = "Download  Upload File ",tags= {"Notes","User"},description = "Download file ")
	@GetMapping("/download/{id}")
	@PreAuthorize(ROLE_ADMIN_USER)
	public ResponseEntity<?> downloadFile(@PathVariable Integer id) throws Exception;
	
	
	@Operation(summary = "Get All notes ",tags= {"Notes"},description = "Get All notes By Admin")
	@GetMapping("/")
	@PreAuthorize(ROLE_ADMIN)
	public ResponseEntity<?> getAllNotesss();
	
	
	@Operation(summary = "Get All notes By User ",tags= {"Notes","User"},description = "Get All notes By User")
	@GetMapping("/user-notes")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> getAllNotesssByUser(
			@RequestParam(name="pageNo",defaultValue = DEFAULT_PAGE_NO) Integer pageNo,
			@RequestParam(name="pageSize",defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize	);
	
	
	@Operation(summary = "Delete notes ",tags= {"Notes","User"},description = "Delete notes By User")
	@GetMapping("/delete/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> deleteNotes(@PathVariable Integer id) throws Exception;
	
	
	@Operation(summary = "Restore deleted notes  ",tags= {"Notes","User"},description = "Restore deleted notes from recycle bin")
	@GetMapping("/restore/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> restoreNotes(@PathVariable Integer id) throws Exception;
	
	
	@Operation(summary = "Get Notes from recycle bin ",tags= {"Notes","User"},description = "Get Notes from recycle bin")
	@GetMapping("/recycle-bin")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> getUserRecleBinNotes() throws Exception;
	
	
	@Operation(summary = "Hard delete notes ",tags= {"Notes","User"},description = "Hard delete notes by user")
	@DeleteMapping("/delete/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> hardDeleteNotes(@PathVariable Integer id) throws Exception;
	
	
	
	@Operation(summary = "Empty user recycle bin ",tags= {"Notes","User"},description = "Empty user recycle bin by user")
	@DeleteMapping("/delete")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> emptyUserRecycleBine()throws Exception;
	
	
	@Operation(summary = "Favourite Notes ",tags= {"Notes","User"},description = " User Favourite Notes")
	@GetMapping("/fav/{noteId}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> favouriteNotes(@PathVariable Integer noteId )throws Exception;
	
	
	@Operation(summary = "UnFavourite Notes ",tags= {"Notes","User"},description = "User UnFavourite Notes")
	@PreAuthorize(ROLE_USER)
	@DeleteMapping("/un-fav/{favNoteId}")
	public ResponseEntity<?> unFavouriteNotes(@PathVariable Integer favNoteId)throws Exception;
	
	
	@Operation(summary = "Get User favourite notes ",tags= {"Notes","User"},description = "Get User favourite notes")
	@GetMapping("/fav-note")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> getUserFavouritNotes()throws Exception;
	
	
	@Operation(summary = "Copy User ",tags= {"Notes","User"},description = "copy notes")
	@GetMapping("/copy/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> copyNotes(@PathVariable Integer id )throws Exception;
	
	
	@Operation(summary = "Search Notes ",tags= {"Notes","User"},description = "User Search notes ")
	@GetMapping("/search")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> searchNotes(@RequestParam (name="key",defaultValue = "") String  key ,
			@RequestParam(name="pageNo",defaultValue = DEFAULT_PAGE_NO) Integer pageNo,
			@RequestParam(name="pageSize",defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize	);
	
}
