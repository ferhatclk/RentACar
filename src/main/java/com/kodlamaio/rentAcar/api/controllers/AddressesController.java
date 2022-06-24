package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.AddressService;
import com.kodlamaio.rentAcar.bussines.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateBillingAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateContactAddressRequest;
import com.kodlamaio.rentAcar.bussines.response.address.GetAllAddressResponse;
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

	@PostMapping("add")
	public Result add(@RequestBody CreateAddressRequest createAddressRequest) {
		return addressService.add(createAddressRequest);
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
}
