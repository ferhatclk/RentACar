package com.kodlamaio.rentAcar.bussines.abstracts;

import com.kodlamaio.rentAcar.bussines.request.users.CreateUserRequest;
import com.kodlamaio.rentAcar.bussines.request.users.DeleteUserRequest;
import com.kodlamaio.rentAcar.bussines.request.users.UpdateUserRequest;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface UserService {
	Result add(CreateUserRequest createUserRequest);
	Result delete(DeleteUserRequest deleteUserRequest);
	Result update(UpdateUserRequest updateUserRequest);
	//List<List<GetAllUsersResponse>> getAll();
}
