package com.dhapola.blog.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhapola.blog.payload.ApiResponse;
import com.dhapola.blog.payload.UserDto;
import com.dhapola.blog.services.UserService;

import jakarta.validation.Valid;

//This is where we control all the API or endpoints

@RestController
@RequestMapping("/api/users")
public class UserControllers {

	@Autowired
	private UserService userService;

	// Create User id
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	}

	// Update user by ID
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
		UserDto updatedUserDto = this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUserDto);
	}

	// Delete User Id
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> delteUser(@PathVariable("userId") Integer uid) {
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true, LocalTime.now()),
				HttpStatus.OK);
	}

	// Deleting All User at a time
	@DeleteMapping("/")
	public ResponseEntity<ApiResponse> delteAllUser() {
		this.userService.deleteAllUsers();
		return new ResponseEntity<ApiResponse>(new ApiResponse("All User deleted Successfully", true,LocalTime.now()), HttpStatus.OK);
	}

	// Get All user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return ResponseEntity.ok(this.userService.getAllUser());
	}

	// Get user ById
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

}
