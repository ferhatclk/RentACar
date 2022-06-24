package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.AdditionalService;
import com.kodlamaio.rentAcar.bussines.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentAcar.bussines.request.additionals.DeleteAdditionalRequest;
import com.kodlamaio.rentAcar.bussines.request.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentAcar.bussines.response.additionals.GetAllAdditionalsResponse;
import com.kodlamaio.rentAcar.bussines.response.additionals.GetByIdAdditionalResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/additionals")
public class AdditionalsController {

	private AdditionalService additionalService;

	@Autowired
	public AdditionalsController(AdditionalService additionalService) {
		this.additionalService = additionalService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalRequest createAdditionalRequest) {
		return additionalService.add(createAdditionalRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteAdditionalRequest deleteAdditionalRequest) {
		return additionalService.delete(deleteAdditionalRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateAdditionalRequest updateAdditionalRequest) {
		return additionalService.update(updateAdditionalRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllAdditionalsResponse>> getAll(){
		return additionalService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetByIdAdditionalResponse> getById(int id){
		return additionalService.getById(id);
	}
}
