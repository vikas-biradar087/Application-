package com.example.blog.service;

import java.util.List;

//import com.example.blog.entity.Post;
import com.example.blog.payload.PostDto;
import com.example.blog.payload.PostResponse;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto,long id,long cid);
	
   //update
	PostDto updatePost(PostDto postDto,long id);
	
	//delete
	void deletePost(long id);
//	
//	//getall post
	PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
//	
//	//get single post
	PostDto getPostById(long id);
	
	//get all post by category
	
	List<PostDto> getPostByCategory(long id);
	
	//get all post by user
	
	List<PostDto> getPostByUser(long uid);
	
//	//get search post
	List<PostDto>searchPosts(String Keyword);
}
