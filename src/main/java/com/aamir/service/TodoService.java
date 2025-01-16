package com.aamir.service;

import java.util.List;

import com.aamir.dto.TodoDto;

public interface TodoService {
	
public boolean saveTodo(TodoDto todo) throws Exception;

public TodoDto getTodoById(Integer id) throws Exception;

public List<TodoDto> getTodoByUser();
}
