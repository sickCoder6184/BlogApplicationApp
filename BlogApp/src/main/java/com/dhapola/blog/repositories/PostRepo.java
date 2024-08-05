package com.dhapola.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dhapola.blog.entities.Category;
import com.dhapola.blog.entities.Post;
import com.dhapola.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer > {
	
	Page<Post> findByUser(User user, Pageable pageable); //finding all post by the that one user
	
	Page<Post> findByCategory(Category category, Pageable pageable); //finding all post from that one category
	
	List<Post> findByTitleContaining(String title); 
}
