package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.RentalService;
import com.kodlamaio.rentAcar.bussines.request.rentals.CreateRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.DeleteRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.UpdateRentalRequest;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetRentalResponse;
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
	public Result addForIndividual(@RequestBody CreateRentalRequest createRentalRequest) {
		return rentalService.addForIndividual(createRentalRequest);
	}
	
	@PostMapping("/addForCorporate")
	public Result addForCorporate(@RequestBody CreateRentalRequest createRentalRequest) {
		return rentalService.addForCorporate(createRentalRequest);
	}
	
	@PostMapping("/deleteForIndividual")
	public Result deleteForIndividual(DeleteRentalRequest deleteRentalRequest) {
		return rentalService.deleteForIndividual(deleteRentalRequest);
	}
	
	@PostMapping("/deleteForCorporate")
	public Result deleteForCorporate(DeleteRentalRequest deleteRentalRequest) {
		return rentalService.deleteForCorporate(deleteRentalRequest);
	}
	
	@PostMapping("/updateForIndividual")
	public Result updateForIndividual(@RequestBody UpdateRentalRequest updateRentalRequest) {
		return rentalService.updateForIndividual(updateRentalRequest);
	}
	
	@PostMapping("/updateForCorporate")
	public Result updateForCorporate(@RequestBody UpdateRentalRequest updateRentalRequest) {
		return rentalService.updateForCorporate(updateRentalRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllRentalsResponse>> getAll(GetAllRentalsResponse getAllRentalsResponse){
		return rentalService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetRentalResponse> getById(int id){
		return rentalService.getById(id);
	}
	
}
