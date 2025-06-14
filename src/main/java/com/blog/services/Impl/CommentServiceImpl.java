package com.blog.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CommentDTO;
import com.blog.repositories.CommentRepositories;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepositories commentRepositories;

	@Override
	public CommentDTO createCommect(CommentDTO commentdto, Integer postId) {
		return null;
	}

	@Override
	public void deletComment(Integer commentId) {

	}

}
