package com.kodlamaio.rentAcar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.CorporateCustomerService;
import com.kodlamaio.rentAcar.bussines.request.corporateCustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.corporateCustomers.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.corporateCustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.bussines.response.corporateCustomers.GetAllCorporateCustomersResponse;
import com.kodlamaio.rentAcar.bussines.response.corporateCustomers.GetByIdCorporateCustomerResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/corporateCustomers")
public class CorporateCustomersController {
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		this.corporateCustomerService = corporateCustomerService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequet) throws NumberFormatException, RemoteException {
		return this.corporateCustomerService.add(createCorporateCustomerRequet);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllCorporateCustomersResponse>> getAll(){
		return corporateCustomerService.getAll();
	}

	
	@GetMapping("/getbyid")
	public DataResult<GetByIdCorporateCustomerResponse> getById(int id){
		return corporateCustomerService.getById(id);
	}
}
