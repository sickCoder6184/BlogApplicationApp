package com.dhapola.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhapola.blog.entities.User;
import com.dhapola.blog.payload.UserDto;
import com.dhapola.blog.repositories.UserRepo;
import com.dhapola.blog.services.UserService;
import com.dhapola.blog.exception.*;

//In this section implementation of methods of service logic will be done 
//we have implemented the UserService(interface) in the UserServiceImpl(class) which will have implemention of all methods of UserService.
//API method ke implementation

@Service
public class UserServiceImpl implements UserService {

	// to perform CRUD operation on user we need userRepo as it has Jpa Repositories

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper; 

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToUser(userDto);// => changed userEntity to userDto

		User savedUser = this.userRepo.save(user);// => The changed userEntity is saved in savedUser

		return this.userToDto(savedUser);// =>returning savedUser by again converting in Dto
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		// We getting the information of user by using find by id method and handelling
		// the exception
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		// Here we setting the user value to be updated
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		// Here we converting the updatedUser into Usrdto

		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);

		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {

		List<User> listOfUsers = this.userRepo.findAll();

		List<UserDto> listOfUserDtos = listOfUsers.stream().map(user -> this.userToDto(user))
				.collect(Collectors.toList());

		return listOfUserDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		this.userRepo.delete(user);
	}
	
	@Override
	public void deleteAllUsers() {
	    List<User> users = (List<User>) this.userRepo.findAll();

	    for (User user : users) {
	        this.userRepo.delete(user);
	    }
	}

	// We have to do conversion, we can do by model mapper and another method is
	// this
	// making methods which will change dto to userEntity and userEntity to dto.
	// Done specially for complex models

	private User dtoToUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}

	public UserDto userToDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		
		return userDto;
	}

	


}
