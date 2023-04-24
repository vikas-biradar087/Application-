package com.example.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.UserDto;
import com.example.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	//localhost:8080/api/user
	@PostMapping
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
		if(bindingResult.hasErrors()){

            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
			UserDto createUser = userService.createUser(userDto);
			return new ResponseEntity<Object>(createUser,HttpStatus.CREATED);
	}
	
	//localhost:8080/api/user/1
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable long id ){
		UserDto userById = userService.getUserById(id);
		return new ResponseEntity<UserDto>(userById,HttpStatus.OK);
	}
	
	//localhost:8080/api/user
	@GetMapping
	public ResponseEntity<List<UserDto>> getAll(){
		return  ResponseEntity.ok(this.userService.fetchAll());
		
	}
	
	//localhost:8080/api/user/1
	@PutMapping("{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") long id){
	UserDto updateUser = userService.updateUser(userDto, id);
	return ResponseEntity.ok(updateUser);
	}
	
	
	
	//localhost:8080/api/user/1
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponse> deleteRecord(@PathVariable("id") long id){
		userService.deleteById(id);
		return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}


}
