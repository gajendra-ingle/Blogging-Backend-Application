package com.blog.services;

import com.blog.dto.CommentDTO;

public interface CommentService {

	// create
	CommentDTO createCommect(CommentDTO commentdto, Integer postId);

	// deleted Comment
	void deletComment(Integer commentId);
}
