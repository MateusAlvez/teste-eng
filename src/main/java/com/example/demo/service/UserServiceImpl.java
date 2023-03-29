package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	private UserRepository userRepository;

	@Override
	public void saveUser(UserDto userDto) { 
        User user = new User();
        user.setName(userDto.getName());
        user.setPostname(userDto.getName());
        user.setEmail(userDto.getEmail());

        userRepository.save(user);
	 }

	@Override
	public List<UserDto> findAllUsers() { 
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());}
	
    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setName(str[0]);
        userDto.setPostname(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

	@Override
	public User findUserByEmail(String email) { 
	return userRepository.findByEmail(email); }


}
