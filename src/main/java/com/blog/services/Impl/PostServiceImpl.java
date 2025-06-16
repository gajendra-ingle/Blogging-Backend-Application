package com.blog.services.Impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDTO;
import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.expections.ResourseNotFoundExceptions;
import com.blog.repositories.CategoryRepositories;
import com.blog.repositories.PostRepositories;
import com.blog.repositories.UserRepository;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepositories postRepositories;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepositories categoryRepositories;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDTO createPost(PostDTO postDto, Integer userId, Integer catId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundExceptions("user", "userId", userId));

		Category category = categoryRepositories.findById(catId)
				.orElseThrow(() -> new ResourseNotFoundExceptions("Category", "category Id", catId));

		Post post = modelMapper.map(postDto, Post.class);
		post.setPostImageName("Default.img");
		post.setPostAddedData(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = postRepositories.save(post);

		return modelMapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDto, Integer postId) {
		Post post = postRepositories.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundExceptions("Post", "post Id", postId));

		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImageName(postDto.getPostImageName());

		Post updatedPost = postRepositories.save(post);

		return modelMapper.map(updatedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepositories.findById(postId)
				.orElseThrow(() -> new ResourseNotFoundExceptions("Post", "post Id", postId));
		postRepositories.delete(post);
	}

	@Override
	public List<PostDTO> getAllPosts() {
		return null;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		return null;
	}

	@Override
	public List<PostDTO> getAllPostByUser(Integer userId) {
		return null;
	}

	@Override
	public List<PostDTO> getAllPostByCategory(Integer categoryId) {
		return null;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		return null;
	}

}
