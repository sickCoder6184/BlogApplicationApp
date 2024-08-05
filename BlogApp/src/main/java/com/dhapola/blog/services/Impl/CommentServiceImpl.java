package com.dhapola.blog.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.dhapola.blog.entities.Comment;
import com.dhapola.blog.entities.Post;
import com.dhapola.blog.entities.User;
import com.dhapola.blog.exception.ResourceNotFoundException;
import com.dhapola.blog.payload.CommentDto;
import com.dhapola.blog.repositories.CommentRepo;
import com.dhapola.blog.repositories.PostRepo;
import com.dhapola.blog.repositories.UserRepo;
import com.dhapola.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createCommentDto(CommentDto commentDto, Integer postId,Integer userId) {
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
		
		
		User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		comment.setUser(user);
		
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment com=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "commentId", commentId));
		
		this.commentRepo.delete(com);

	}

}
