package com.kodlamaio.rentAcar.bussines.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.IndividualCustomerService;
import com.kodlamaio.rentAcar.bussines.request.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.individualCustomers.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.individualCustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.bussines.response.individualCustomers.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentAcar.bussines.response.individualCustomers.GetByIdIndividualCustomerResponse;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.mernis.PersonCheckService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentAcar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{
	
	private IndividualCustomerRepository individualCustomerRepository;
	private ModelMapperService modelMapperService;
	private PersonCheckService personCheckService;
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerRepository individualCustomerRepository,
			ModelMapperService modelMapperService, PersonCheckService personCheckService) {
		this.individualCustomerRepository = individualCustomerRepository;
		this.modelMapperService = modelMapperService;
		this.personCheckService = personCheckService;
	}
	
	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException {
		notRepeatNationalIdentity(createIndividualCustomerRequest.getNationalIdentity());
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		checkIfPerson(individualCustomer);
		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL.ADDED");
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		individualCustomerRepository.deleteById(deleteIndividualCustomerRequest.getIndividualCustomerId());
		return new SuccessResult("INDIVIDUAL.DELETED");
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
//		IndividualCustomer individualCustomer = individualCustomerRepository.findById(updateIndividualCustomerRequest.getIndividualCustomerId());
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
//		user.setNationalIdentity(individualCustomer.getNationalIdentity());
		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("INDIVIDUAL.UPDATE");
	}

	@Override
	public DataResult<List<GetAllIndividualCustomersResponse>> getAll() {
		List<IndividualCustomer> users = individualCustomerRepository.findAll();
		List<GetAllIndividualCustomersResponse> response = users.stream().map(user -> modelMapperService.forResponse()
				.map(user, GetAllIndividualCustomersResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualCustomersResponse>>(response);
	}
	
	@Override
	public DataResult<List<GetAllIndividualCustomersResponse>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		List<IndividualCustomer> users = this.individualCustomerRepository.findAll(pageable).getContent();
		
		List<GetAllIndividualCustomersResponse> response = users.stream().map(user -> this.modelMapperService.forResponse()
				.map(user, GetAllIndividualCustomersResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllIndividualCustomersResponse>>(response) ;
	}

	@Override
	public DataResult<GetByIdIndividualCustomerResponse> getById(int id) {
		IndividualCustomer user = individualCustomerRepository.findById(id);
		GetByIdIndividualCustomerResponse response = modelMapperService.forResponse().map(user, GetByIdIndividualCustomerResponse.class);
		
		return new SuccessDataResult<GetByIdIndividualCustomerResponse>(response);
	}
	
	private void checkIfPerson(IndividualCustomer user) throws NumberFormatException, RemoteException {
		if(!personCheckService.checkPerson(user)) {
			throw new BusinessException("USER.NOT.AVAILABLE");
		}
	}
	
	
	private void notRepeatNationalIdentity(String nationalIdentity) {
		IndividualCustomer user = individualCustomerRepository.findByNationalIdentity(nationalIdentity);
		if(user != null) {
			throw new BusinessException("USER.EXİST.!!!");
		}
	}
}
