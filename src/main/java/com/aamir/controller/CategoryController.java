package com.aamir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import com.aamir.dto.CategoryDto;
import com.aamir.dto.CategoryResponse;
import com.aamir.endpoint.CategoryEndpoint;
import com.aamir.entity.Category;
import com.aamir.exception.ResourceNotFoundException;
import com.aamir.service.CategoryService;
import com.aamir.util.CommonUtil;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController

public class CategoryController implements CategoryEndpoint {
	@Autowired
	private CategoryService categoryService;

	@Override
	public ResponseEntity<?> saveCategory( CategoryDto categoryDto) {
		boolean saveCategory = categoryService.saveCategory(categoryDto);
		if (saveCategory) {
			// generic msg dikhana hai or status avi niche wala commmt kr liya
			return CommonUtil.createBuildResponseMessage("saved success", HttpStatus.CREATED);
			//return new ResponseEntity<>("saved successfully", HttpStatus.CREATED);
		} else {
			//generic agr save nhi hua to error ane wala hai niche wala commet
			return CommonUtil.createErrorResponseMessage(" category not saved", HttpStatus.INTERNAL_SERVER_ERROR);
			//return new ResponseEntity<>(" not saved ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	public ResponseEntity<?> getallCategory() {
		List<CategoryDto> allCategory = categoryService.getAllCategory();

		if (CollectionUtils.isEmpty(allCategory)) {
			// agr empty h
			return ResponseEntity.noContent().build();
		} else {
			// object or status niche wal comm
			return CommonUtil.createBuildResponse(allCategory, HttpStatus.OK);
			//return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}
	@Override
	public ResponseEntity<?> getactiveCategory() {
		List<CategoryResponse> allCategory = categoryService.getActiveCategory();

		if (CollectionUtils.isEmpty(allCategory)) {
			// agr empty h
			return ResponseEntity.noContent().build();
		} else {
			//return new ResponseEntity<>(allCategory, HttpStatus.OK);
			return CommonUtil.createBuildResponse(allCategory, HttpStatus.OK);

		}
	}
	@Override
	public ResponseEntity<?> getCategoryDetailsById( Integer id) throws Exception {
			CategoryDto categoryDto = categoryService.getCategoryById(id);

			if (ObjectUtils.isEmpty(categoryDto)) {
				// agr null h
				return CommonUtil.createErrorResponseMessage("category not found ", HttpStatus.NOT_FOUND);

				//return new ResponseEntity<>("category not found ", HttpStatus.NOT_FOUND);
			}
			//return new ResponseEntity<>(categoryDto, HttpStatus.OK);
			return CommonUtil.createBuildResponse(categoryDto, HttpStatus.OK);


	}
	@Override
	public ResponseEntity<?> deleteCategoryDetailsById( Integer id) {
		Boolean deleted = categoryService.deleteCategory(id);

		if (deleted) {
			// agr delete ho gya
			return CommonUtil.createBuildResponse("category delete successfully", HttpStatus.OK);

			//return new ResponseEntity<>("category delete successfully", HttpStatus.OK);
		} else {
			//return new ResponseEntity<>("category not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
			return CommonUtil.createErrorResponseMessage("category not deleted", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
