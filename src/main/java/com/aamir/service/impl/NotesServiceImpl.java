package com.aamir.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.aamir.dto.CategoryResponse;
import com.aamir.dto.NotesDto;
import com.aamir.dto.NotesDto.CategoryDto;
import com.aamir.entity.Notes;
import com.aamir.exception.ResourceNotFoundException;
import com.aamir.repository.CategoryRepository;
import com.aamir.repository.NotesRepository;
import com.aamir.service.NotesService;
@Service
public class NotesServiceImpl implements NotesService{
	
	@Autowired
private NotesRepository notesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public boolean saveNotes(NotesDto notesDto) throws Exception {
		// validation last baad me ( category ka id post me send kr rhe h to validate krna h ki us id ke across category hai ki nhi)
		//validation check ke liye ek method hi bna leta hu
		  checkCategoryExist(notesDto.getCategory());
		
		// ye upper wala last me
		Notes notes = modelMapper.map(notesDto, Notes.class);
		Notes savenotes = notesRepository.save(notes);
		if(!ObjectUtils.isEmpty(savenotes)) {
			return true;
		}
		return false;
	}

	private void checkCategoryExist(CategoryDto category) throws Exception {
		// custom bnaye the resourcenotfoundexception ussi ko call krna hai
		categoryRepository.findById(category.getId()).orElseThrow(()->new ResourceNotFoundException("category id is invalid"));
		
	}

	@Override
	public List<NotesDto> getAllNotes() {
		//advance way
		return notesRepository.findAll().stream().map(note->modelMapper.map(note, NotesDto.class)).toList();	
	}

}
