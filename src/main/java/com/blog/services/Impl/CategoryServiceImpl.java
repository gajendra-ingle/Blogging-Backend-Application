package com.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CategoryDTO;
import com.blog.entities.Category;
import com.blog.expections.ResourceNotFoundException;
import com.blog.repositories.CategoryRepositories;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepositories categoryRepositories;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO, Category.class);
		Category savingCategory = categoryRepositories.save(category);
		return modelMapper.map(savingCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {

		Category category = categoryRepositories.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category Id", categoryId));

		category.setCategoryTitle(categoryDTO.getCategoryTitle());
		category.setCategoryDescription(categoryDTO.getCategoryDescription());

		Category updatedCategory = categoryRepositories.save(category);
		return modelMapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategoryById(Integer categoryId) {
		Category category = categoryRepositories.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		return modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public void deletCategroy(Integer categoryId) {
		Category category = categoryRepositories.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

		categoryRepositories.delete(category);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> allCategory = categoryRepositories.findAll();
		List<CategoryDTO> categoryDto = allCategory.stream()
				.map(category -> modelMapper.map(category, CategoryDTO.class))
				.collect(Collectors.toList());
		return categoryDto;
	}

}
