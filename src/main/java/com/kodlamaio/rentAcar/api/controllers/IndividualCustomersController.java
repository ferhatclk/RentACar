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

import com.kodlamaio.rentAcar.bussines.abstracts.IndividualCustomerService;
import com.kodlamaio.rentAcar.bussines.request.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.individualCustomers.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.individualCustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.bussines.response.individualCustomers.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentAcar.bussines.response.individualCustomers.GetByIdIndividualCustomerResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/individualCustomers")
public class IndividualCustomersController {
	
	private IndividualCustomerService individualCustomerService;
	
	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		this.individualCustomerService = individualCustomerService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException {
		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllIndividualCustomersResponse>> getAll(){
		return individualCustomerService.getAll();
	}
	
	@GetMapping("/getallbypage")
	public DataResult<List<GetAllIndividualCustomersResponse>> getAll(@RequestParam int pageNo, int pageSize){
		return individualCustomerService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetByIdIndividualCustomerResponse> getById(int id){
		return individualCustomerService.getById(id);
	}
}
