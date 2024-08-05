package com.dhapola.blog.services;

import java.util.List;

import com.dhapola.blog.payload.UserDto;

//For creating API make all methods in service layer and the implementation in impl service
// Api ke methods
public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUser();
	void deleteUser(Integer id);
	void deleteAllUsers();
}
