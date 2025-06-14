package com.blog.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.entities.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDTO {

	@NotBlank(message = "Post title is required")
	@Size(min = 5, max = 100, message = "Post title must be between 5 and 100 characters")
	private String postTitle;

	@NotBlank(message = "Post content is required")
	private String postContent;

	private String postImageName;

	@NotNull(message = "Post date must be provided")
	@PastOrPresent(message = "Post date cannot be in the future")
	private Date postAddedData;

	@NotNull(message = "Category must be provided")
	private CategoryDTO category;

	@NotNull(message = "User must be provided")
	private UserDTO user;

	private List<Comment> comment = new ArrayList<>();

}
