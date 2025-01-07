package com.aamir.service.impl;

import java.util.List;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aamir.dto.TodoDto;
import com.aamir.dto.TodoDto.StatusDto;
import com.aamir.entity.Todo;
import com.aamir.enums.TodoStatus;
import com.aamir.exception.ResourceNotFoundException;
import com.aamir.repository.TodoRepository;
import com.aamir.service.TodoService;
import com.aamir.util.Validation;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
private TodoRepository todoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
		
	@Autowired
	private Validation validation;
	
	
	@Override
	public boolean saveTodo(TodoDto todoDto) throws Exception {
		//validate todo status ,util pakage ke validation class me bnalenge
		validation.todoValidation(todoDto);
		//tododto h lekin hume entity save krna h
		Todo todo = modelMapper.map(todoDto,Todo.class);
		todo.setStatusId(todoDto.getStatus().getId());
		Todo saveTodo = todoRepository.save(todo);
		if(!ObjectUtils.isEmpty(saveTodo)) {
			return true;
		}
		return false;
	}

	@Override
	public TodoDto getTodoById(Integer id) throws Exception {
		Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found ! id is invalid"));
		//entity mila h todo to usse todoDto me convert krna hai
		TodoDto todoDto = modelMapper.map(todo, TodoDto.class);
		// status name nhi aa rha h qki hum primitive type de rhe hai object nhi
		setStatus(todoDto,todo);
		return todoDto;
	}

	private void setStatus(TodoDto todoDto, Todo todo) {
		// name hum enum se get krenge
		for( TodoStatus st:TodoStatus.values()) {
			if(st.getId().equals(todo.getStatusId())) {
				StatusDto statusDto=StatusDto.builder()
						.id(st.getId())
						.name(st.getName())
						.build();
				
				todoDto.setStatus(statusDto);
			}
		}
		
	}

	@Override
	public List<TodoDto> getTodoByUser() {
		//abhi user module nhi kiye hai to static user ko le rhe hai 1, qki auditing me 1 id liya h
		Integer userId=1;
		List<Todo> todos=todoRepository.findByCreatedBy(userId);
		//to convert in todoDto by useing stream
		 List<TodoDto> todolist = todos.stream().map(td->modelMapper.map(td, TodoDto.class)).toList();
		return todolist;
	}

}
