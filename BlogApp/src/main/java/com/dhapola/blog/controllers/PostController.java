package com.dhapola.blog.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhapola.blog.config.AppConstant;
import com.dhapola.blog.entities.Post;
import com.dhapola.blog.payload.ApiResponse;
import com.dhapola.blog.payload.PostDto;
import com.dhapola.blog.payload.PostResponse;
import com.dhapola.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	// getAll

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy) {
		PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// deleteAll
	@DeleteMapping("/")
	public ResponseEntity<ApiResponse> delteAllUser() {
		this.postService.deleteAllPost();
		return new ResponseEntity<ApiResponse>(new ApiResponse("All post deleted Successfully", true, LocalTime.now()),
				HttpStatus.OK);
	}

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

	}

	// get by user

	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize) {
		PostResponse postResponse = this.postService.getPostsByUser(userId, pageNumber, pageSize);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	// get by Category

	@GetMapping("/posts/category/{categoryId}")
	public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize) {
		PostResponse postResponse = this.postService.getPostsByCategory(categoryId, pageNumber, pageSize);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	// delete

	@DeleteMapping("/post/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is Deleted", true, LocalTime.now());
	}

	// update

	@PutMapping("post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatePost = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {

		List<PostDto> result = this.postService.searchPost(keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}

}
