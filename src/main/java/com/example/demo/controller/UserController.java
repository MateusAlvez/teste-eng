package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired UserService userService;
	@Autowired UserRepository userRepository;

	@GetMapping("/")
	public String home(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);

		return "index";
	}

	@ResponseBody
	@GetMapping("/users")
	public List<User> getUsers() { return userRepository.findAll(); }

	@PostMapping("/")
	@ResponseBody
	public String registration(@ModelAttribute("user") UserDto userDto,Model model) {
		User existingUser = userService.findUserByEmail(userDto.getEmail());

		if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			return "Já existe uma conta com esse email!";
		}

		userService.saveUser(userDto);
		return "Usuário registrado com sucesso!";
	}

}
