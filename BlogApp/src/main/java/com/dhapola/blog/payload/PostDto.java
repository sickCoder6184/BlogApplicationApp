package com.dhapola.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.dhapola.blog.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private String id;
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	
	private CategoryDto category;
	
	private UserDto user;
	
	//while fetching post WE Will get the comment
	private Set<CommentDto> comments=new HashSet<>();//-->The Api for get All is done here
	
	// If we want to apply pagination than we have to build the CRUD API For Comments
	
}
