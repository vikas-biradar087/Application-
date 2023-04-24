package com.example.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.Category;

public interface CategoryRepositories extends JpaRepository<Category, Long> {

}
