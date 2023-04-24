package com.example.blog.service;

import java.util.List;

//import com.example.blog.payload.ApiResponse;
//import com.example.blog.entity.User;
import com.example.blog.payload.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto usertdto);
	
	UserDto updateUser(UserDto userdto,long id);
	
	UserDto getUserById(long id);
	
	List <UserDto> fetchAll();
	
	void deleteById(long id);

}
