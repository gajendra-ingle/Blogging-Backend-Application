package com.blog.services;

import java.util.List;

import com.blog.dto.CategoryDTO;

public interface CategoryService {

	// create
	CategoryDTO createCategory(CategoryDTO categoryDTO);

	// update
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

	// get

	CategoryDTO getCategoryById(Integer categoryId);

	// Delete
	void deletCategroy(Integer categoryId);

	// get all category

	List<CategoryDTO> getAllCategory();

}
