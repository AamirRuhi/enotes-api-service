package com.aamir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aamir.dto.TodoDto;
import com.aamir.service.TodoService;
import com.aamir.util.CommonUtil;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@PostMapping("/")
	public ResponseEntity<?> saveTodo(@RequestBody TodoDto todoDto) throws Exception{
		boolean saveTodo = todoService.saveTodo(todoDto);
		if(saveTodo) {
			
			return CommonUtil.createBuildResponseMessage("todo saved success", HttpStatus.CREATED);
		}else {
		return CommonUtil.createErrorResponseMessage("todo not saved ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> saveTodo(@PathVariable Integer id) throws Exception{
	 TodoDto todo = todoService.getTodoById(id);
	 
		return CommonUtil.createBuildResponse(todo, HttpStatus.OK);
		
	
	}
	@GetMapping("/list")
	public ResponseEntity<?> getAllTodoByUser() throws Exception{
		List<TodoDto> todoList = todoService.getTodoByUser();
		if(CollectionUtils.isEmpty(todoList)) {
			return ResponseEntity.noContent().build();
			// response body me kuch nhi jana chahiye
		}
		return CommonUtil.createBuildResponse(todoList , HttpStatus.OK);
		
		
	}

}
