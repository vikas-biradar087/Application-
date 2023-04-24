package com.example.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Category;
import com.example.blog.exception.ResourceNotFoundException;
//import com.example.blog.entity.Category;
//import com.example.blog.entity.User;
import com.example.blog.payload.CategoryDto;
//import com.example.blog.payload.UserDto;
//import com.example.blog.payload.UserDto;
import com.example.blog.repositories.CategoryRepositories;

@SuppressWarnings("unused")
@Service
public class CategoryServiceimpl implements CategoryService {
	
	@Autowired
	private CategoryRepositories cateRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	//create
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category createCat = this.modelMapper.map(categoryDto, Category.class);
		Category saveData = this.cateRepo.save(createCat);
		return modelMapper.map(saveData, CategoryDto.class);
	}

	//update
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, long id) {
		Category updateCate = cateRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category","id", id));
					
			
			updateCate.setCategoryTitle(categoryDto.getCategoryTitle());
			updateCate.setCategoryDescription(categoryDto.getCategoryDescription());
			Category saveUpdate = cateRepo.save(updateCate);
			
		return modelMapper.map(saveUpdate,CategoryDto.class);
	}

	//delete
	@Override
	public void deleteCategory(long id) {
		Category deleteCate = cateRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Category", "id", id));
		this.cateRepo.delete(deleteCate);
		
	}

	//Read
	@Override
	public CategoryDto getCatById(long id) {
	 
		Category getCate = cateRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Category", "id", id)
				);
		
		return modelMapper.map(getCate, CategoryDto.class);
	}

	//getall
	@Override
	public List<CategoryDto> fetchAll() {
		List<Category> getAll = cateRepo.findAll();
		List<CategoryDto> collect = getAll.stream().map((x)-> modelMapper.map(x,CategoryDto.class)).collect(Collectors.toList());
		return collect;
	}
	
//	private Category dtoToCategory(UserDto userDto){
//		Category cat=new Category();
//		cat=modelMapper.map(userDto, Category.class);
//		return cat;
//		
//	   }
//	
//	private CategoryDto categoryToDto(CategoryDto categoryDto, Class<Category> class1) {
//		CategoryDto cdto=new CategoryDto();
//		cdto=modelMapper.map(categoryDto,CategoryDto.class );
//		return cdto;
//	}
	}


