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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Category", description ="All the category operation Apis")
@RequestMapping("/api/v1/category")
public interface CategoryEndpoint {
	
	@Operation(summary = " Save Category ",tags= {"Category"},description = "Admin Save Category")
	@PostMapping("/save-category")
	@PreAuthorize(ROLE_ADMIN)//only accessed by admin ,annotaion ko enable krne ke liye @EnableMethodSecurity in SecurityConfig
	public ResponseEntity<?> saveCategory( @RequestBody CategoryDto categoryDto);
	
	
	@Operation(summary = "Get All Category ",tags= {"Category"} ,description = "Admin Get All Category")
	@GetMapping("/")
	@PreAuthorize(ROLE_ADMIN)//only accessed by admin ,annotaion ko enable krne ke liye @EnableMethodSecurity in SecurityConfig
	public ResponseEntity<?> getallCategory();
	
	
	@Operation(summary = "Get Active Category ",tags= {"Category"} ,description = "Admin and User can Get Active Category")
	@GetMapping("/active-category")
	//@PreAuthorize("hasAnyRole('USER','ADMIN')")//only accessed by both,annotaion ko enable krne ke liye @EnableMethodSecurity in SecurityConfig
	@PreAuthorize(ROLE_ADMIN_USER)
	public ResponseEntity<?> getactiveCategory();
	
	
	@Operation(summary = "Get Category By Id ",tags= {"Category"} ,description = "Admin Get Category Details ")
	@GetMapping("/{id}")
	@PreAuthorize(ROLE_ADMIN)
	public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) throws Exception;
	
	
	@Operation(summary = "Delete Category By Id ",tags= {"Category"} ,description = "Admin Delete Category")
	@DeleteMapping("/{id}")
	@PreAuthorize(ROLE_ADMIN)
	public ResponseEntity<?> deleteCategoryDetailsById(@PathVariable Integer id);
}
