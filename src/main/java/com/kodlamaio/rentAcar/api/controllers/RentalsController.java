package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.RentalService;
import com.kodlamaio.rentAcar.bussines.request.rentals.CreateCorporateCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.CreateIndividualCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.DeleteCorporateCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.DeleteIndividualCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.UpdateCorporateCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.UpdateIndividualCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetAllCorporateCustomerRentalsResponse;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetAllIndividualCustomerRentalsResponse;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetByIdCorporateCustomerRentalResponse;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetByIdIndividualCustomerRentalResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	private RentalService rentalService;
	
	@Autowired
	public RentalsController(RentalService rentalService) {
		this.rentalService = rentalService;
	}
	
	@PostMapping("/addForIndividual")
	public Result addForIndividual(@RequestBody CreateIndividualCustomerRentalRequest createIndividualCustomerRentalRequest) {
		return rentalService.addForIndividual(createIndividualCustomerRentalRequest);
	}
	
	@PostMapping("/addForCorporate")
	public Result addForCorporate(@RequestBody CreateCorporateCustomerRentalRequest createCorporateCustomerRentalRequest) {
		return rentalService.addForCorporate(createCorporateCustomerRentalRequest);
	}
	
	@PostMapping("/deleteForIndividual")
	public Result deleteForIndividual(DeleteIndividualCustomerRentalRequest deleteIndividualCustomerRentalRequest) {
		return rentalService.deleteForIndividual(deleteIndividualCustomerRentalRequest);
	}
	
	@PostMapping("/deleteForCorporate")
	public Result deleteForCorporate(DeleteCorporateCustomerRentalRequest deleteCorporateCustomerRentalRequest) {
		return rentalService.deleteForCorporate(deleteCorporateCustomerRentalRequest);
	}
	
	@PostMapping("/updateForIndividual")
	public Result updateForIndividual(@RequestBody UpdateIndividualCustomerRentalRequest updateIndividualCustomerRentalRequest) {
		return rentalService.updateForIndividual(updateIndividualCustomerRentalRequest);
	}
	
	@PostMapping("/updateForCorporate")
	public Result updateForCorporate(@RequestBody UpdateCorporateCustomerRentalRequest updateCorporateCustomerRentalRequest) {
		return rentalService.updateForCorporate(updateCorporateCustomerRentalRequest);
	}
	
	@GetMapping("/getallForIndividual")
	public DataResult<List<GetAllIndividualCustomerRentalsResponse>> getAllForIndividualRentals(GetAllIndividualCustomerRentalsResponse getAllIndividualCustomerRentalsResponse){
		return rentalService.getAllForIndividualRentals();
	}
	
	@GetMapping("/getallForCorporate")
	public DataResult<List<GetAllCorporateCustomerRentalsResponse>> getAllForCorporateRentals(GetAllCorporateCustomerRentalsResponse getAllCorporateCustomerRentalsResponse){
		return rentalService.getAllForCorporateRentals();
	}
	
	@GetMapping("/getbyidForIndividual")
	public DataResult<GetByIdIndividualCustomerRentalResponse> getByIdForIndividualRental(int id){
		return rentalService.getByIdForIndividualRental(id);
	}
	
	@GetMapping("/getbyidForCorporate")
	public DataResult<GetByIdCorporateCustomerRentalResponse> getByIdForCorporateRental(int id){
		return rentalService.getByIdForCorporateRental(id);
	}
	
}
