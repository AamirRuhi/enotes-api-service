package com.aamir.service;

import java.util.List;

import com.aamir.entity.Category;

public interface CategoryService {
public boolean saveCategory(Category category);

public List<Category>  getAllCategory();
}
