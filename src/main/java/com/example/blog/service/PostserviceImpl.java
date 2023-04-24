package com.example.blog.service;

//import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payload.PostDto;
import com.example.blog.payload.PostResponse;
import com.example.blog.repositories.CategoryRepositories;
import com.example.blog.repositories.PostRepository;
import com.example.blog.repositories.UserRepository;

@Service
public class PostserviceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepositories cateRepo;

	//createpost
	@Override
	public PostDto createPost(PostDto postDto,long id,long cid) {
		
				User user=userRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("User", "user id", id)
				);
		
		Category category=cateRepo.findById(cid).orElseThrow(
				()-> new ResourceNotFoundException("Category", "category id", cid)
				);
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=postRepo.save(post);
		return modelMapper.map(newPost, PostDto.class);
	}
	
	
	//update post
	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		Post upPost = postRepo.findById(id).orElseThrow(
				
				()-> new ResourceNotFoundException("Post", "post id", id)
				
				);
		upPost.setTitle(postDto.getTitle());
		upPost.setContent(postDto.getContent());
		upPost.setImageName(postDto.getImageName());
		upPost.setAddedDate(postDto.getAddedDate());
		
		return modelMapper.map(upPost, PostDto.class);
	}

//	//deletePost
	@Override
	public void deletePost(long id) {
		Post deletepost = postRepo.findById(id).orElseThrow(
				
				()-> new ResourceNotFoundException("Post", "post id", id)
				
				);
		this.postRepo.delete(deletepost);
		
	}

	//getAllposts
	@Override
	public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepo.findAll(pageable);

        // get content for page object
        List<Post> content = posts.getContent();


	List<PostDto> contents = content.stream().map((x->modelMapper.map(x, PostDto.class))).collect(Collectors.toList());
	
	PostResponse postResponse=new PostResponse();
    postResponse.setContent(contents);
    postResponse.setPageNo(posts.getNumber());
    postResponse.setPageSize(posts.getSize());
    postResponse.setTotalPages(posts.getTotalPages());
    postResponse.setTotalElement(posts.getTotalElements());
    postResponse.setLast(posts.isLast());

    return postResponse;

	}

//	//getsinglepost
	@Override
	public PostDto getPostById(long id) {
	Post getPost = postRepo.findById(id).orElseThrow(
			
			()-> new ResourceNotFoundException("Post", "post id", id)
			);
		return modelMapper.map(getPost, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(long id) {
				Category cat=cateRepo.findById(id).orElseThrow(
						
						()-> new ResourceNotFoundException("Category", "category id", id));
				
				List<Post> findCat = postRepo.findByCategory(cat);
					List<PostDto> collects = findCat.stream().map((x)-> modelMapper.map(x, PostDto.class)).collect(Collectors.toList());
		return collects;
	}

	
	
	@Override
	public List<PostDto> getPostByUser(long uid) {
		
		User user=userRepo.findById(uid).orElseThrow(
				
				()-> new ResourceNotFoundException("User", "user id", uid));
		
		List<Post> findUser = postRepo.findByUser(user);
			List<PostDto> postUser = findUser.stream().map((x)-> modelMapper.map(x, PostDto.class)).collect(Collectors.toList());

		return postUser;
	}

	@Override
	public List<PostDto> searchPosts(String Keyword) {
		
		List<Post> posts = postRepo.searchByTitleContaining("%"+Keyword+"%");
		List<PostDto> collectTitle = posts.stream().map((x->modelMapper.map(x, PostDto.class))).collect(Collectors.toList());
	
		return  collectTitle;
	}





	


}
