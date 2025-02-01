package com.aamir.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//constant ko import krenge,ab ROLE_ADMIN direct use kr skte hai
import static com.aamir.util.Constants.ROLE_ADMIN;
import static com.aamir.util.Constants.ROLE_ADMIN_USER;
import com.aamir.dto.CategoryDto;

@RequestMapping("/api/v1/category")
public interface CategoryEndpoint {
	@PostMapping("/save-category")
	@PreAuthorize(ROLE_ADMIN)//only accessed by admin ,annotaion ko enable krne ke liye @EnableMethodSecurity in SecurityConfig
	public ResponseEntity<?> saveCategory( @RequestBody CategoryDto categoryDto);
	
	
	@GetMapping("/")
	@PreAuthorize(ROLE_ADMIN)//only accessed by admin ,annotaion ko enable krne ke liye @EnableMethodSecurity in SecurityConfig
	public ResponseEntity<?> getallCategory();
	
	
	@GetMapping("/active-category")
	//@PreAuthorize("hasAnyRole('USER','ADMIN')")//only accessed by both,annotaion ko enable krne ke liye @EnableMethodSecurity in SecurityConfig
	@PreAuthorize(ROLE_ADMIN_USER)
	public ResponseEntity<?> getactiveCategory();
	
	
	@GetMapping("/{id}")
	@PreAuthorize(ROLE_ADMIN)
	public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) throws Exception;
	
	@DeleteMapping("/{id}")
	@PreAuthorize(ROLE_ADMIN)
	public ResponseEntity<?> deleteCategoryDetailsById(@PathVariable Integer id);
}
