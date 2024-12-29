package com.aamir.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.aamir.dto.CategoryDto;
import com.aamir.dto.CategoryResponse;
import com.aamir.entity.Category;
import com.aamir.exception.ResourceNotFoundException;
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
		//update bhi isi method se kr lenge id send krke
		if(ObjectUtils.isEmpty(category.getId())) {
			category.setIsDeleted(false);
			category.setCreatedBy(1);
			category.setCreatedOn(new Date());
			
		}else {
			//iske liye method niche bna liya
			updateCategory(category);
		}
		Category savecategory = categoryRepository.save(category);
		if (ObjectUtils.isEmpty(savecategory)) {
			return false;
		}

		return true;
	}

	private void updateCategory(Category category) {
		Optional<Category> findbyId = categoryRepository.findById(category.getId());
		if(findbyId.isPresent()) {
			Category exitcategory = findbyId.get();
			//ye update nhi hona chahiye
			category.setCreatedBy(exitcategory.getCreatedBy());
			category.setCreatedOn(exitcategory.getCreatedOn());
			category.setIsDeleted(exitcategory.getIsDeleted());
			//kon update kiya or kab kiya
			category.setUpdatedBy(1);
			category.setUpdatedOn(new Date());
		}
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		//List<Category> categories = categoryRepository.findAll();
		List<Category> categories = categoryRepository.findByIsDeletedFalse();
		//category mila to stream api se categoryDto me convert krenge
		List<CategoryDto> categoryDtoList = categories.stream().map(cat->modelMapper.map(cat, CategoryDto.class)).toList();
		return categoryDtoList;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() {
		//abhi active or inactive dono aya ab condition lga denge
		//List<Category> categories = categoryRepository.findByIsActiveTrue();
		List<Category> categories = categoryRepository.findByIsActiveTrueAndIsDeletedFalse();
		//ab active category mil gya hai ab response
		List<CategoryResponse> categorylist = categories.stream().map(cat->modelMapper.map(cat, CategoryResponse.class)).toList();
		return categorylist;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) throws Exception {
		//Optional<Category> findbycategory = categoryRepository.findById(id);
	Category category = categoryRepository.findByIdAndIsDeletedFalse(id)
				.orElseThrow(()->new ResourceNotFoundException("category not found with id="+id));
		if(!ObjectUtils.isEmpty(category)) {
			category.getName().toUpperCase();
			CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
			return categoryDto;
		}
		return null;
	}

	@Override
	public Boolean deleteCategory(Integer id) {
		//db me isdeleted 0 ->false means delete nhi hai 1 ->true means deled h, koi delete krta h to true kr denge ke baad save kr denge
		//lekin getmapping kr rha hu 2 id ka delete krne ke baad fir bhi dekh rha hai
		//to condition lgayenge ki isDelete agre true h to na dikhe (getCategoryById me)
		// gelAllcategory me abhi bhi dikh rha hai
		// ab active-category wale me dikh rha hai
		Optional<Category> findbycategory = categoryRepository.findById(id);
		if(findbycategory.isPresent()) {
			Category category = findbycategory.get();
			category.setIsDeleted(true);
			categoryRepository.save(category);
		return true;
	}
		return false;

}
}