package com.dhapola.blog.controllers;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhapola.blog.payload.ApiResponse;
import com.dhapola.blog.payload.CommentDto;
import com.dhapola.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class ComentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/user/{userId}")
	public ResponseEntity<CommentDto> createComent(@RequestBody CommentDto commentdto, 
			@PathVariable Integer postId,
			@PathVariable Integer userId) {
		CommentDto createComment = this.commentService.createCommentDto(commentdto, postId,userId);
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.OK);

	}

	@DeleteMapping("/{commentId}")
	public ApiResponse deleteComment(@PathVariable Integer commentId) {
		this.commentService.deleteComment(commentId);
		return new ApiResponse("Comment is Deleted", true, LocalTime.now());
	}
}
