package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.users.CreateUserRequest;
import com.kodlamaio.rentAcar.bussines.request.users.DeleteUserRequest;
import com.kodlamaio.rentAcar.bussines.request.users.UpdateUserRequest;
import com.kodlamaio.rentAcar.bussines.response.users.GetAllUsersResponse;
import com.kodlamaio.rentAcar.bussines.response.users.GetByIdUserResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface UserService {
	Result add(CreateUserRequest createUserRequest);
	Result delete(DeleteUserRequest deleteUserRequest);
	Result update(UpdateUserRequest updateUserRequest);
	DataResult<List<GetAllUsersResponse>> getAll();
	DataResult<List<GetAllUsersResponse>> getAll(int pageNo, int pageSize);
	DataResult<GetByIdUserResponse> getById(int id);
}
