package com.blog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDTO {

	private Integer categoryId;

	@NotEmpty(message = "Category title is required")
	@Size(min = 3, max = 100, message = "Category title must be between 3 and 100 characters")
	private String categoryTitle;

	@NotEmpty(message = "Category description is required")
	@Size(min = 5, max = 250, message = "Category description must be between 5 and 250 characters")
	private String categoryDiscription;
}
