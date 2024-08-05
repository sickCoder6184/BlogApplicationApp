package com.dhapola.blog.services.Impl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dhapola.blog.entities.Category;
import com.dhapola.blog.entities.Post;
import com.dhapola.blog.entities.User;
import com.dhapola.blog.exception.ResourceNotFoundException;
import com.dhapola.blog.payload.CategoryDto;
import com.dhapola.blog.payload.PostDto;
import com.dhapola.blog.payload.PostResponse;
import com.dhapola.blog.payload.UserDto;
import com.dhapola.blog.repositories.CategoryRepo;
import com.dhapola.blog.repositories.PostRepo;
import com.dhapola.blog.repositories.UserRepo;
import com.dhapola.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo; // to perform DB operation

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "User Id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category Id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);

		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO update method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()-> 
		new ResourceNotFoundException("post","post id", postId));
		
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO delete method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()-> 
		new ResourceNotFoundException("post","post id", postId));
		this.postRepo.delete(post);
	}
	@Override
	public void deleteAllPost() {
		// TODO deleteAll method stub
		List<Post> allPost=this.postRepo.findAll();
		
		for (Post p:allPost) {
			this.postRepo.delete(p);
		}
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy) {
		
		PageRequest p=PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		
		// TODO getAll method stub
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPosts=pagePost.getContent();
		
		List<PostDto> postsDto = allPosts.stream().map((x) -> this.modelMapper.map(x, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postsDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPagesize(pagePost.getSize());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}

	@Override
	public Post getPostById(Integer postId) {

		return null;
	}

	//get post by category
	
	@Override
	public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {
	    Category cat = this.categoryRepo.findById(categoryId)
	            .orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));

	    
	    PageRequest pageable = PageRequest.of(pageNumber, pageSize);

	    Page<Post> pagePosts = this.postRepo.findByCategory(cat, pageable);
	    List<Post> postsByCategory = pagePosts.getContent();

	    List<PostDto> postDtos = postsByCategory.stream()
	            .map(post -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());

	    PostResponse postResponse = new PostResponse();
	    postResponse.setContent(postDtos);
	    postResponse.setPageNumber(pagePosts.getNumber());
	    postResponse.setPagesize(pagePosts.getSize());
	    postResponse.setTotalPages(pagePosts.getTotalPages());
	    postResponse.setTotalElements(pagePosts.getTotalElements());
	    postResponse.setLastPage(pagePosts.isLast());

	    return postResponse;
	}

	
	
	//get post By user
	@Override
	public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize) {
	    User user = this.userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("user", "user id", userId));

	    PageRequest pageable = PageRequest.of(pageNumber, pageSize);

	    Page<Post> pagePosts = this.postRepo.findByUser(user, pageable);
	    List<Post> postsByUser = pagePosts.getContent();

	    List<PostDto> postDtos = postsByUser.stream()
	            .map(post -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());

	    PostResponse postResponse = new PostResponse();
	    postResponse.setContent(postDtos);
	    postResponse.setPageNumber(pagePosts.getNumber());
	    postResponse.setPagesize(pagePosts.getSize());
	    postResponse.setTotalPages(pagePosts.getTotalPages());
	    postResponse.setTotalElements(pagePosts.getTotalElements());
	    postResponse.setLastPage(pagePosts.isLast());

	    return postResponse;
	}


	@Override
	public List<PostDto> searchPost(String keyword) {
		
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((p)->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
