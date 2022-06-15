package com.kodlamaio.rentAcar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.UserService;
import com.kodlamaio.rentAcar.bussines.request.users.CreateUserRequest;
import com.kodlamaio.rentAcar.bussines.request.users.DeleteUserRequest;
import com.kodlamaio.rentAcar.bussines.request.users.UpdateUserRequest;
import com.kodlamaio.rentAcar.bussines.response.users.GetAllUsersResponse;
import com.kodlamaio.rentAcar.bussines.response.users.GetByIdUserResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException {
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
	
	@GetMapping("/getall")
	public DataResult<List<GetAllUsersResponse>> getAll(){
		return userService.getAll();
	}
	
	@GetMapping("/getallbypage")
	public DataResult<List<GetAllUsersResponse>> getAll(@RequestParam int pageNo, int pageSize){
		return userService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetByIdUserResponse> getById(int id){
		return userService.getById(id);
	}
}
