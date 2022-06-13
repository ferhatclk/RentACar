package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.UserService;
import com.kodlamaio.rentAcar.bussines.request.users.CreateUserRequest;
import com.kodlamaio.rentAcar.bussines.request.users.DeleteUserRequest;
import com.kodlamaio.rentAcar.bussines.request.users.UpdateUserRequest;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.UserRespository;
import com.kodlamaio.rentAcar.entities.concretes.User;

@Service
public class UserManager implements UserService{
	@Autowired
	private UserRespository userRespository;
	@Autowired
	private ModelMapperService modelMapperService;

	@Override
	public Result add(CreateUserRequest createUserRequest) {
		notRepeatTcNo(createUserRequest.getTcNo());
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
		this.userRespository.save(user);
		return new SuccessResult("USER.ADDED");
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		userRespository.deleteById(deleteUserRequest.getId());
		return new SuccessResult("USER.DELETED");
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userRespository.save(user);
		return new SuccessResult("USER.UPDATE");
	}
	
	
	private void notRepeatTcNo(String tcNo) {
		List<User> users = userRespository.findAll();
		for (User item : users) {
			if(item.getTcNo() == tcNo) {
				throw new BusinessException("USER.NOT.EXİST");
			}
		}
		
	}

}
