package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public interface UserService {
	
	void saveUser(UserDto user);
	
	User findUserByEmail(String email);
	
	List<UserDto> findAllUsers();

}
