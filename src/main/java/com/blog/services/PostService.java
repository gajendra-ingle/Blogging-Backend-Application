package com.blog.services;

import java.util.List;

import com.blog.dto.PostDTO;
import com.blog.services.Impl.pageNumber;

public interface PostService {

	// create
	PostDTO createPost(PostDTO postDto, Integer userId, Integer catId);

	// update
	PostDTO updatePost(PostDTO postdto, Integer postId);

	// deleted
	void deletePost(Integer postId);

	// get all post
	List<PostDTO> getAllPosts(Integer pageNumber, Integer pageSize);

	// get single post
	PostDTO getPostById(Integer postId);

	// get all post by uses
	List<PostDTO> getAllPostByUser(Integer userId);

	// get all post by categories
	List<PostDTO> getAllPostByCategory(Integer categoryId);

	// search posts
	List<PostDTO> searchPosts(String keyword);
}
