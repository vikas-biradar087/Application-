package com.example.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
