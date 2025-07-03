package com.blog.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CommentDTO;
import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.expections.ResourceNotFoundException;
import com.blog.repositories.CommentRepositories;
import com.blog.repositories.PostRepositories;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepositories commentRepositories;

	@Autowired
	private PostRepositories postRepositories;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createCommect(CommentDTO commentDto, Integer postId) {
		Post post = postRepositories.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id ", postId));

		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = commentRepositories.save(comment);
		return modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void deletComment(Integer commentId) {
		Comment comment = commentRepositories.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
		commentRepositories.delete(comment);
	}

}
