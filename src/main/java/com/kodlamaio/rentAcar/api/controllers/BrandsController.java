package com.kodlamaio.rentAcar.api.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.BrandService;
import com.kodlamaio.rentAcar.bussines.request.brands.CreateBrandRequest;
import com.kodlamaio.rentAcar.bussines.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentAcar.bussines.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentAcar.bussines.response.brands.GetAllBrandsResponse;
import com.kodlamaio.rentAcar.bussines.response.brands.GetByIdBrandResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	
	private BrandService brandService;
	
	@Autowired
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@PostMapping("/add")  // endpoint
	public Result add(@RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		return brandService.delete(deleteBrandRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		return brandService.update(updateBrandRequest);
	}
	
	@GetMapping("/getall") 
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		 
		return brandService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetByIdBrandResponse> GetById(int id) {
		return brandService.getById(id);
	}
	
}
