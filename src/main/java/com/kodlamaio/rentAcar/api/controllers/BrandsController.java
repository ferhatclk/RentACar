package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.BrandService;
import com.kodlamaio.rentAcar.bussines.request.brands.CreateBrandRequest;
import com.kodlamaio.rentAcar.bussines.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentAcar.bussines.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.entities.concretes.Brand;

//localhost:8080/api/brands/sayhello
@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	
	private BrandService brandService;
	
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}

	@GetMapping("/getall")  // endpoint
	public DataResult<List<Brand>> getAll() {
		 
		return brandService.getAll();
	}
	
	@PostMapping("/add")
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
	
	@GetMapping("/getbyid")
	public DataResult<Brand> GetById(int id) {
		return brandService.getById(id);
	}
}
