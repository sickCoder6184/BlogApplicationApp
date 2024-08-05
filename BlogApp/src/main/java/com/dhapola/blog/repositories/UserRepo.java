package com.dhapola.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhapola.blog.entities.User;

//This interface will help to perform data base operation on the 
//entity with the help of JpaRepository

public interface UserRepo extends JpaRepository<User, Integer>{

}
