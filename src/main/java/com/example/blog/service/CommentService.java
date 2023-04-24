package com.example.blog.service;

import com.example.blog.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentdto,long id);
	
	void deleteComment(long id);

}
