package com.aamir.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aamir.dto.CategoryDto;
import com.aamir.dto.CategoryResponse;
import com.aamir.entity.Category;
import com.aamir.repository.CategoryRepository;
import com.aamir.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public boolean saveCategory(CategoryDto categoryDto) {
		// save hone ke baad category delete krenge na isdeleted=false krna h,
		// setCreatedBy(1) abhi user nhi h,isActive api se send krenge
//		category.setIsDeleted(false);
//		category.setCreatedBy(1);
//		category.setCreatedOn(new Date());
//		Category savecategory = categoryRepository.save(category);
//		if(ObjectUtils.isEmpty(savecategory)) {
//			return false;
//		}

		// *** ye sab request me chahiye baki response me chahiye bna lenge CategoryResponse
		/*
		 * Category category = new Category(); category.setName(categoryDto.getName());
		 * category.setDescription(categoryDto.getDescription());
		 * category.setIsActive(categoryDto.getIsActive());
		 * category.setIsDeleted(false);
	    *	category.setCreatedBy(1);
	     *	category.setCreatedOn(new Date());
		 */		//ye category bnake set kr rhe hai yahi pr modelmapper ka concept ata hai
		
		Category category = modelMapper.map(categoryDto, Category.class);
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category savecategory = categoryRepository.save(category);
		if (ObjectUtils.isEmpty(savecategory)) {
			return false;
		}

		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		//category mila to stream api se categoryDto me convert krenge
		List<CategoryDto> categoryDtoList = categories.stream().map(cat->modelMapper.map(cat, CategoryDto.class)).toList();
		return categoryDtoList;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() {
		//abhi active or inactive dono aya ab condition lga denge
		List<Category> categories = categoryRepository.findByIsActiveTrue();
		//ab active category mil gya hai ab response
		List<CategoryResponse> categorylist = categories.stream().map(cat->modelMapper.map(cat, CategoryResponse.class)).toList();
		return categorylist;
	}

}
