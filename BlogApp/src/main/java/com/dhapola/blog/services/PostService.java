package com.dhapola.blog.services;

import java.util.List;

import com.dhapola.blog.entities.Post;
import com.dhapola.blog.payload.PostDto;
import com.dhapola.blog.payload.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);//create
	
	PostDto updatePost(PostDto postDto,Integer postId);//update
	
	void deletePost(Integer postId);//delete
	void deleteAllPost();//deleteAll
	
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortby);//readAll
	
	Post getPostById(Integer postId);//read one
	
	//All post by category Id
	PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize); 
	
	//All post by user Id
	PostResponse getPostsByUser(Integer UserId,Integer pageNumber, Integer pageSize); 
	
	List<PostDto> searchPost(String keyword);  //search post by keyword
};
