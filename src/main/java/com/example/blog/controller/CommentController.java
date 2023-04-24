package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payload.ApiResponse;
//import com.example.blog.entity.Comment;
import com.example.blog.payload.CommentDto;
import com.example.blog.service.CommentService;

@RestController
@RequestMapping("/api/com")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{id}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable("id") long id){
		CommentDto createComm = commentService.createComment(comment, id);
		return new ResponseEntity<CommentDto>(createComm,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("id") long id){
		commentService.deleteComment(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted comment successfully ", true),HttpStatus.CREATED);
	}

}
