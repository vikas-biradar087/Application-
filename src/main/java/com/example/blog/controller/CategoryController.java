package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.blog.payload.CategoryDto;
import com.example.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/get")
public class CategoryController {
	
	@Autowired
	private CategoryService cateService;
	
	@PostMapping
	public ResponseEntity<Object> createCate(@Valid @RequestBody CategoryDto categoryDto ,BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){

            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
		CategoryDto c = cateService.createCategory(categoryDto);
		return new ResponseEntity<Object>(c,HttpStatus.CREATED);
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getById(@PathVariable("id") long id){
		CategoryDto catId = cateService.getCatById(id);
		return new ResponseEntity<CategoryDto>(catId,HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAll(){
		return ResponseEntity.ok(this.cateService.fetchAll());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCat(@Valid @RequestBody CategoryDto categoryDto ,@PathVariable("id") long id){
		CategoryDto updateC = cateService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(updateC,HttpStatus.OK);
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> delteCat(@PathVariable("id") long id){
		cateService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
}
