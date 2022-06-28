package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.AddressService;
import com.kodlamaio.rentAcar.bussines.request.addresses.CreateCorporateCustomerAddressRequet;
import com.kodlamaio.rentAcar.bussines.request.addresses.CreateIndividualCustomerAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.DeleteCorporateCustomerAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.DeleteIndividualCustomerAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateBillingAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateContactAddressRequest;
import com.kodlamaio.rentAcar.bussines.response.address.GetAllAddressResponse;
import com.kodlamaio.rentAcar.bussines.response.address.GetByIdAddressResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/addresses/")
public class AddressesController {
	
	private AddressService addressService;
	
	@Autowired
	public AddressesController(AddressService addressService) {
		this.addressService = addressService;
	}

	@PostMapping("addIndividualCustomerAddress")
	public Result addIndividualCustomerAddress(@RequestBody CreateIndividualCustomerAddressRequest createIndividualCustomerAddressRequest) {
		return addressService.addIndividualCustomerAddress(createIndividualCustomerAddressRequest);
	}
	
	@PostMapping("addCorporateCustomerAddress")
	public Result addCorporateCustomerAddress(@RequestBody CreateCorporateCustomerAddressRequet createCorporateCustomerAddressRequet) {
		return addressService.addCorporateCustomerAddress(createCorporateCustomerAddressRequet);
	}
	
	@PostMapping("deleteIndividualCustomerAddress")
	public Result deleteIndividualCustomerAddress(DeleteIndividualCustomerAddressRequest deleteIndividualCustomerAddressRequest) {
		return addressService.deleteIndividualCustomerAddress(deleteIndividualCustomerAddressRequest);
	}
	
	@PostMapping("deleteCorporateCustomerAddress")
	public Result deleteCorporateCustomerAddress(DeleteCorporateCustomerAddressRequest deleteCorporateCustomerAddressRequest) {
		return addressService.deleteCorporateCustomerAddress(deleteCorporateCustomerAddressRequest);
	}
	
	@PostMapping("updateContactAddress")
	public Result updateContactAddress(@RequestBody UpdateContactAddressRequest updateContactAddressRequest) {
		return addressService.updateContactAddress(updateContactAddressRequest);
	}
	
	@PostMapping("updateBillingAddress")
	public Result updateBillingAddress(@RequestBody UpdateBillingAddressRequest updateBillingAddressRequest) {
		return addressService.updateBillingAddress(updateBillingAddressRequest);
	}
	
	@GetMapping("getall")
	public DataResult<List<GetAllAddressResponse>> getAll(){
		return addressService.getAll();
	}
	
	@GetMapping("getById")
	public DataResult<GetByIdAddressResponse> getById(int id){
		return addressService.getById(id);
	}
}