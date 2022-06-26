package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.OrderedAdditionalItemService;
import com.kodlamaio.rentAcar.bussines.request.orderedAdditionalItems.CreateOrderedAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.orderedAdditionalItems.DeleteOrderedAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.orderedAdditionalItems.UpdateOrderedAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.response.orderedAdditionalItem.GetAllOrderedAdditionalItemsResponse;
import com.kodlamaio.rentAcar.bussines.response.orderedAdditionalItem.GetByIdOrderedAdditionalItemResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/orderedAdditionalItems")
public class OrderedAdditionalItemsController {

	private OrderedAdditionalItemService additionalService;

	@Autowired
	public OrderedAdditionalItemsController(OrderedAdditionalItemService additionalService) {
		this.additionalService = additionalService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateOrderedAdditionalItemRequest createAdditionalRequest) {
		return additionalService.add(createAdditionalRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteOrderedAdditionalItemRequest deleteAdditionalRequest) {
		return additionalService.delete(deleteAdditionalRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateOrderedAdditionalItemRequest updateAdditionalRequest) {
		return additionalService.update(updateAdditionalRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllOrderedAdditionalItemsResponse>> getAll(){
		return additionalService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetByIdOrderedAdditionalItemResponse> getById(int id){
		return additionalService.getById(id);
	}
}
