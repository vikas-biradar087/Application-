package com.example.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
