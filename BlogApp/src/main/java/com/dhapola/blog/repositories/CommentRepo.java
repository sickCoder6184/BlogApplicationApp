package com.dhapola.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhapola.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	

}
