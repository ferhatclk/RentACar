//package com.kodlamaio.rentAcar.bussines.concretes;
//
//
//import java.rmi.RemoteException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import com.kodlamaio.rentAcar.bussines.abstracts.UserService;
//import com.kodlamaio.rentAcar.bussines.request.users.CreateUserRequest;
//import com.kodlamaio.rentAcar.bussines.request.users.DeleteUserRequest;
//import com.kodlamaio.rentAcar.bussines.request.users.UpdateUserRequest;
//import com.kodlamaio.rentAcar.bussines.response.users.GetAllUsersResponse;
//import com.kodlamaio.rentAcar.bussines.response.users.GetByIdUserResponse;
//import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
//import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
//import com.kodlamaio.rentAcar.core.utilities.mernis.PersonCheckService;
//import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
//import com.kodlamaio.rentAcar.core.utilities.result.Result;
//import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
//import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
//import com.kodlamaio.rentAcar.dataAccess.abstracts.UserRespository;
//import com.kodlamaio.rentAcar.entities.concretes.User;
//
//@Service
//public class UserManager implements UserService{
//
//	private UserRespository userRespository;
//	private ModelMapperService modelMapperService;
//	private PersonCheckService personCheckService;
//	
//	@Autowired
//	public UserManager(UserRespository userRespository, ModelMapperService modelMapperService,
//			PersonCheckService personCheckService) {
//		this.userRespository = userRespository;
//		this.modelMapperService = modelMapperService;
//		this.personCheckService = personCheckService;
//	}
//
//
//	@Override
//	public Result add(CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException {
//		notRepeatNationalIdentity(createUserRequest.getNationalIdentity());
//
//		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
////		checkIfPerson(user);
//		this.userRespository.save(user);
//		return new SuccessResult("USER.ADDED");
//	}
//
//	@Override
//	public Result delete(DeleteUserRequest deleteUserRequest) {
//		userRespository.deleteById(deleteUserRequest.getId());
//		return new SuccessResult("USER.DELETED");
//	}
//
//	@Override
//	public Result update(UpdateUserRequest updateUserRequest) {
//		User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
//		this.userRespository.save(user);
//		return new SuccessResult("USER.UPDATE");
//	}
//	
//	@Override
//	public DataResult<List<GetAllUsersResponse>> getAll() {
//		List<User> users = userRespository.findAll();
//		List<GetAllUsersResponse> response = users.stream().map(user -> modelMapperService.forResponse()
//				.map(user, GetAllUsersResponse.class)).collect(Collectors.toList());
//		return new SuccessDataResult<List<GetAllUsersResponse>>(response);
//	}
//	
//	@Override
//	public DataResult<List<GetAllUsersResponse>> getAll(int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
//		
//		List<User> users = this.userRespository.findAll(pageable).getContent();
//		
//		List<GetAllUsersResponse> response = users.stream().map(user -> this.modelMapperService.forResponse()
//				.map(user, GetAllUsersResponse.class)).collect(Collectors.toList());
//		
//		return new SuccessDataResult<List<GetAllUsersResponse>>(response) ;
//	}
//
//	@Override
//	public DataResult<GetByIdUserResponse> getById(int id) {
//		User user = userRespository.findById(id);
//		GetByIdUserResponse response = modelMapperService.forResponse().map(user, GetByIdUserResponse.class);
//		
//		return new SuccessDataResult<GetByIdUserResponse>(response);
//	}
//	
////	private void checkIfPerson(User user) throws NumberFormatException, RemoteException {
////		if(!personCheckService.checkPerson(user)) {
////			throw new BusinessException("USER.NOT.AVAILABLE");
////		}
////	}
//	
//	
//	private void notRepeatNationalIdentity(String nationalIdentity) {
//		User user = userRespository.findByNationalIdentity(nationalIdentity);
//		if(user != null) {
//			throw new BusinessException("USER.EXİST.!!!");
//		}
//	}
//
//}
