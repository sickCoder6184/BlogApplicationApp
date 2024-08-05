package com.dhapola.blog.services;

import com.dhapola.blog.payload.CommentDto;

public interface CommentService {
	CommentDto createCommentDto(CommentDto commentDto,Integer postId,Integer userId);
	void deleteComment(Integer commentId);
}
