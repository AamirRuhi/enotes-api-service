package com.aamir.service;

import java.util.List;

import com.aamir.dto.CategoryDto;
import com.aamir.dto.CategoryResponse;
import com.aamir.entity.Category;

public interface CategoryService {
public boolean saveCategory(CategoryDto categoryDto);

public List<CategoryDto>  getAllCategory();

public List<CategoryResponse> getActiveCategory();

public CategoryDto getCategoryById(Integer id) throws Exception;

public Boolean deleteCategory(Integer id);
}
