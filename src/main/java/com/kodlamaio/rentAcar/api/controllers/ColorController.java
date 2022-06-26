package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.ColorService;
import com.kodlamaio.rentAcar.bussines.request.colors.CreateColorRequest;
import com.kodlamaio.rentAcar.bussines.request.colors.DeleteColorRequest;
import com.kodlamaio.rentAcar.bussines.request.colors.UpdateColorRequest;
import com.kodlamaio.rentAcar.bussines.response.colors.GetAllColorsResponse;
import com.kodlamaio.rentAcar.bussines.response.colors.GetByIdColorResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/colors")
public class ColorController {
	private ColorService colorService;
	
	@Autowired
	public ColorController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateColorRequest createColorRequest) {
		return this.colorService.add(createColorRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteColorRequest deleteColorRequest) {
		return colorService.delete(deleteColorRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateColorRequest updateColorRequest) {
		return colorService.update(updateColorRequest);
	}
	
	@GetMapping("/getall")  // endpoint
	public DataResult<List<GetAllColorsResponse>>  getAll() {
		return this.colorService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetByIdColorResponse>  GetById(int id) {
		return colorService.getById(id);
	}
	
	
	
}
