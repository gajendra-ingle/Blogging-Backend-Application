package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostRepositories extends JpaRepository<Post, Integer> {
	 
	List<Post> findAllByUser(User user);
	
	List<Post> findAllByCategory(Category category);
	
	List<Post> findByPostTitleContaining(String postTitle);
}
