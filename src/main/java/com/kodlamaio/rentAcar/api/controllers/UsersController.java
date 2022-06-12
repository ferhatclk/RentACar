package com.kodlamaio.rentAcar.api.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.UserService;
import com.kodlamaio.rentAcar.bussines.request.users.CreateUserRequest;
import com.kodlamaio.rentAcar.bussines.request.users.DeleteUserRequest;
import com.kodlamaio.rentAcar.bussines.request.users.UpdateUserRequest;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	private UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest) {
		return this.userService.add(createUserRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteUserRequest deleteUserRequest) {
		return this.userService.delete(deleteUserRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
		return this.userService.update(updateUserRequest);
	}
}
