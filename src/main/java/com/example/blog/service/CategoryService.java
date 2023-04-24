package com.example.blog.service;

import java.util.List;

import com.example.blog.payload.CategoryDto;

public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,long id);
	
	//delete
	void deleteCategory(long id);
	
	//getid
   CategoryDto getCatById(long id);
	
   //Read getall
	List <CategoryDto> fetchAll();
	

}
