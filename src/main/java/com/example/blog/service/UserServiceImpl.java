package com.example.blog.service;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.User;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payload.UserDto;
import com.example.blog.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserServiceImpl(UserRepository userrepo) {
		super();
		this.userRepo = userrepo;
	}

	@Override
	public UserDto createUser(UserDto usertdto) {
		User user=this.dtoToUser(usertdto);
		User saveUser = userRepo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, long id) {
	User user = userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User","id", id));

				
			user.setName(userdto.getName());
			user.setEmail(userdto.getEmail());
			user.setAbout(userdto.getAbout());
			user.setPassword(userdto.getPassword());
			User updateUser = userRepo.save(user);
			UserDto userDto1 = userToDto(updateUser);
			return userDto1;
	}

	@Override
	public UserDto getUserById(long id) {
		User u = userRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("User", "id", id)
				);
		
		return userToDto(u);
	}

	@Override
	public List<UserDto> fetchAll() {
		List<User> users = userRepo.findAll();
		List<UserDto> userDto2 = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDto2;
	}
	
	@Override
	public void deleteById(long id) {
	User user = userRepo.findById(id).orElseThrow(
			
			()-> new ResourceNotFoundException("User", "id", id)
			);
	
	this.userRepo.delete(user);
	}
	
    // convert  DTO to entity
    private User dtoToUser(UserDto userDto){
    	User user=modelMapper.map(userDto,User.class);
    	return user;
//    	User user=new User();
//    	user.setId(userDto.getId());
//    	user.setEmail(userDto.getEmail());
//    	user.setName(userDto.getName());
//    	user.setAbout(userDto.getAbout());
//    	user.setPassword(userDto.getPassword());
//		return user;
        
    }
    
 // convert entity into dto 
    private UserDto userToDto(User user) {
    	UserDto userDto=modelMapper.map(user,UserDto.class);
    	return userDto;
//    	UserDto userdto=new UserDto();
//    	userdto.setId(user.getId());
//    	userdto.setEmail(user.getEmail());
//    	userdto.setName(user.getName());
//    	userdto.setAbout(user.getAbout());
//    	userdto.setPassword(user.getPassword());
//		return userdto;
    	
    }

	


}
