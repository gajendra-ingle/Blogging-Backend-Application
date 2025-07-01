package com.blog.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDTO;
import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.expections.ResourceNotFoundException;
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
				.orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));

		Category category = categoryRepositories.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", catId));

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
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));

		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImageName(postDto.getPostImageName());

		Post updatedPost = postRepositories.save(post);
		return modelMapper.map(updatedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepositories.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
		postRepositories.delete(post);
	}

	@Override
	public List<PostDTO> getAllPosts(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = postRepositories.findAll(pageable);
		List<Post> content = pagePost.getContent();

		List<PostDTO> allPosts = content.stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		return allPosts;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post = postRepositories.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getAllPostByUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		List<Post> posts = postRepositories.findAllByUser(user);

		List<PostDTO> allPosts = posts.stream().map(post -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		return allPosts;
	}

	@Override
	public List<PostDTO> getAllPostByCategory(Integer categoryId) {
		Category category = categoryRepositories.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));

		List<Post> posts = postRepositories.findAllByCategory(category);

		List<PostDTO> postsDto = posts.stream().map((post) -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		return postsDto;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		return null;
	}

}
