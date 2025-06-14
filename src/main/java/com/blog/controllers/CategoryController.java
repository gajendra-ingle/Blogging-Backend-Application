package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.ApiResponse;
import com.blog.dto.CategoryDTO;
import com.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO cate = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(cate, HttpStatus.CREATED);
	}

	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
			@PathVariable("catId") Integer catId) {
		CategoryDTO cate = categoryService.updateCategory(categoryDTO, catId);
		return new ResponseEntity<CategoryDTO>(cate, HttpStatus.OK);
	}

	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDTO> getById(@PathVariable("catId") Integer catId) {
		CategoryDTO cate = categoryService.getCategoryById(catId);
		return new ResponseEntity<CategoryDTO>(cate, HttpStatus.OK);
	}

	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deletbyId(@PathVariable("catId") Integer catId) {
		categoryService.deletCategroy(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully", false), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<CategoryDTO>> getAll() {
		List<CategoryDTO> allCategory = categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDTO>>(allCategory, HttpStatus.OK);
	}

}
