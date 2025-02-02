package com.aamir.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//constant ko import krenge,ab ROLE_ADMIN direct use kr skte hai
import static com.aamir.util.Constants.ROLE_USER;

import com.aamir.dto.TodoDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Todo", description ="All the Todo  Apis")
@RequestMapping("/api/v1/todo")
public interface TodoEndpoint {
	
	
	@Operation(summary = "Save Todo ",tags= {"Todo"},description = "User save todo")
	@PostMapping("/")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> saveTodo(@RequestBody TodoDto todoDto) throws Exception;
	
	
	@Operation(summary = "Get Todo  ",tags= {"Todo"},description = "Get Todo by id")
	@GetMapping("/{id}")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> getTodoById(@PathVariable Integer id) throws Exception;
	
	
	@Operation(summary = "Get All Todo by user ",tags= {"Todo"},description = "Get All Todo by user")
	@GetMapping("/list")
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<?> getAllTodoByUser() throws Exception;
}
