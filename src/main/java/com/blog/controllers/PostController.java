package com.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstants;
import com.blog.dto.ApiResponse;
import com.blog.dto.PostDTO;
import com.blog.dto.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

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
	public ResponseEntity<PostResponse> getAllPostS(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection) {
		PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

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

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keyword") String postTitle) {
		List<PostDTO> searchPosts = postService.searchPosts(postTitle);
		return new ResponseEntity<List<PostDTO>>(searchPosts, HttpStatus.OK);
	}

	// post image upload
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {

		PostDTO postDto = postService.getPostById(postId);

		String fileName = fileService.uploadImage(path, image);
		postDto.setPostImageName(fileName);
		PostDTO updatePost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}

	// method to serve files
	@GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}
