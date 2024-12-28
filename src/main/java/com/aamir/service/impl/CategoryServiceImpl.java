package com.aamir.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aamir.entity.Category;
import com.aamir.repository.CategoryRepository;
import com.aamir.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	
	
@Autowired
private CategoryRepository categoryRepository;
	
	
	@Override
	public boolean saveCategory(Category category) {
		//save hone ke baad category delete krenge na isdeleted=false krna h, setCreatedBy(1) abhi user nhi h,isActive api se send krenge
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category savecategory = categoryRepository.save(category);
		if(ObjectUtils.isEmpty(savecategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

}
