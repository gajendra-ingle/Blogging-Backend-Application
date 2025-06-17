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
import com.blog.dto.PostDTO;
import com.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post/")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createNewPost(@Valid @RequestBody PostDTO postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDTO createdPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDTO>(createdPost, HttpStatus.CREATED);

	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostDTO> updatesPost(@RequestBody PostDTO postdto, @PathVariable Integer postId) {
		PostDTO pdto = postService.updatePost(postdto, postId);
		return new ResponseEntity<PostDTO>(pdto, HttpStatus.OK);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletedPost(@PathVariable Integer postId) {
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("this post has been deleted", true), HttpStatus.OK);

	}

	@GetMapping("/all")
	public ResponseEntity<List<PostDTO>> getAllPostS() {
		List<PostDTO> listpost = postService.getAllPosts();
		return new ResponseEntity<List<PostDTO>>(listpost, HttpStatus.OK);

	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
		PostDTO postDto = postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(postDto, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getAllPostByUsers(@PathVariable Integer userId) {
		List<PostDTO> post = postService.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(post, HttpStatus.OK);

	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getAllPostByCategory(@PathVariable Integer categoryId) {
		List<PostDTO> post = postService.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(post, HttpStatus.OK);
	}

}
