package com.example.blog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payload.CommentDto;
import com.example.blog.repositories.CommentRepository;
import com.example.blog.repositories.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentdto, long id) {
		Post post = postRepo.findById(id).orElseThrow(
				
				()-> new ResourceNotFoundException("Comment", "comment id", id));
		Comment comment = this.modelMapper.map(commentdto, Comment.class);
		comment.setPost(post);
		Comment saveComment = commentRepo.save(comment);
		
		return modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(long id) {
		Comment comments = commentRepo.findById(id).orElseThrow(
				
				()-> new ResourceNotFoundException("comment", "comment id", id));
		commentRepo.delete(comments);
		
	}

}
