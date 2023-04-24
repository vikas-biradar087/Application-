package com.example.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.example.blog.entity.Post;
import com.example.blog.payload.ApiResponse;
//import com.example.blog.entity.Post;
//import com.example.blog.payload.ApiResponse;
//import com.example.blog.entity.Post;
import com.example.blog.payload.PostDto;
import com.example.blog.payload.PostResponse;
import com.example.blog.service.FileService;
import com.example.blog.service.PostService;
import com.example.blog.utilities.AppConstants;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/Data")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{id}/{cid}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable("id") long id,
			@PathVariable("cid") long cid) {
		PostDto create = postService.createPost(postDto, id, cid);
		return new ResponseEntity<PostDto>(create, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("id") long id){
		PostDto updatePost = postService.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}

	//get Alldata
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAll(
		
		@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false)  int pageNo,
		@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
		@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORTBY, required = false) String sortBy,
		 @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
		    ){
//		        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
		        return ResponseEntity.ok(this.postService.getAllPost(pageNo, pageSize, sortBy, sortDir));

		    }


	

	// get post

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPost(@PathVariable("id") long id) {
		PostDto postById = postService.getPostById(id);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}
	
	//deletepost
	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponse> deleteRecord(@PathVariable("id") long id){
		postService.deletePost(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully !!",true),HttpStatus.OK);
	}

  
	@GetMapping("/user/{id}/posts")
	public ResponseEntity<List<PostDto>> getByPostUser(@PathVariable("id") long id){
		List<PostDto> postByU= postService.getPostByUser(id);
		return new ResponseEntity<List<PostDto>>(postByU,HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{id}/posts")
	public ResponseEntity<List<PostDto>> getByPostBycategory(@PathVariable("id") long id){
		List<PostDto> postByC= postService.getPostByCategory(id);
		return new ResponseEntity<List<PostDto>>(postByC,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> serachPostTitle(
			@PathVariable("keywords") String keywords
		
			){
		List<PostDto> searchPosts = postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.OK);
		
	}
	
	@PostMapping("/posts/image/upload/{id}")
	public ResponseEntity<PostDto> uploadImage(
			
			@RequestParam("image") MultipartFile image,
			@PathVariable("id") long id) throws IOException{
		
		PostDto postDtos = postService.getPostById(id);
		String fileName = fileService.uploadImage(path, image);
		
		postDtos.setImageName(fileName);
		PostDto updatePost = postService.updatePost(postDtos, id);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,
			HttpServletResponse response
	) throws IOException {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
		
	}
	
}
