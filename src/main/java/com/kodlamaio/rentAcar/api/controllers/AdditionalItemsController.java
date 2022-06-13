package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.AdditionalItemService;
import com.kodlamaio.rentAcar.bussines.request.additionalItems.CreateAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.additionalItems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.response.additionalItems.GetAllAdditionalItemsResponse;
import com.kodlamaio.rentAcar.bussines.response.additionalItems.GetByIdAdditionalItemResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/additionalItems")
public class AdditionalItemsController {
	@Autowired
	private AdditionalItemService additionalItemService;

	public AdditionalItemsController(AdditionalItemService additionalItemService) {
		this.additionalItemService = additionalItemService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalItemRequest createAdditionalItemRequest) {
		return this.additionalItemService.add(createAdditionalItemRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		return this.additionalItemService.delete(deleteAdditionalItemRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		return this.additionalItemService.update(updateAdditionalItemRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllAdditionalItemsResponse>> getAll(){
		
		return this.additionalItemService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetByIdAdditionalItemResponse> getById(int id){
		
		return this.additionalItemService.getById(id);
	}
}
