package com.blog.services;

import com.blog.dto.CommentDTO;

public interface CommentService {

	// create
	CommentDTO createCommect(CommentDTO commentDto, Integer postId);

	// deleted Comment
	void deletComment(Integer commentId);
}
