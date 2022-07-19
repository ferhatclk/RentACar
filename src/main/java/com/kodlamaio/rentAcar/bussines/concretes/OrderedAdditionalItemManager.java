package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.AdditionalItemService;
import com.kodlamaio.rentAcar.bussines.abstracts.OrderedAdditionalItemService;
import com.kodlamaio.rentAcar.bussines.abstracts.RentalService;
import com.kodlamaio.rentAcar.bussines.request.orderedAdditionalItems.CreateOrderedAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.orderedAdditionalItems.DeleteOrderedAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.orderedAdditionalItems.UpdateOrderedAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.response.orderedAdditionalItem.GetAllOrderedAdditionalItemsResponse;
import com.kodlamaio.rentAcar.bussines.response.orderedAdditionalItem.GetByIdOrderedAdditionalItemResponse;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.OrderedAdditionalItemRepository;
import com.kodlamaio.rentAcar.entities.concretes.AdditionalItem;
import com.kodlamaio.rentAcar.entities.concretes.OrderedAdditionalItem;
import com.kodlamaio.rentAcar.entities.concretes.Rental;

@Service
public class OrderedAdditionalItemManager implements OrderedAdditionalItemService{
	
	private OrderedAdditionalItemRepository orderedAdditionalItemRepository;
	
	private ModelMapperService modelMapperService;

	private RentalService rentalService;

	private AdditionalItemService additionalItemService;

	@Autowired
	public OrderedAdditionalItemManager(ModelMapperService modelMapperService, OrderedAdditionalItemRepository orderedAdditionalItemRepository,
			RentalService rentalService, AdditionalItemService additionalItemService) {
		this.modelMapperService = modelMapperService;
		this.orderedAdditionalItemRepository = orderedAdditionalItemRepository;
		this.rentalService = rentalService;
		this.additionalItemService = additionalItemService;
	}

	@Override
	public Result add(CreateOrderedAdditionalItemRequest createAdditionalRequest) {
//		checkIfAdditionalItem(createAdditionalRequest.getAdditionalItemId());
		AdditionalItem additionalItem = additionalItemService.getByAdditionalItemId(createAdditionalRequest.getAdditionalItemId());
		Rental rental = rentalService.getByRentalId(createAdditionalRequest.getRentalId());
		OrderedAdditionalItem additional = modelMapperService.forRequest().map(createAdditionalRequest, OrderedAdditionalItem.class);
		
		additional.setDays(rental.getTotalDays());

		double price = additionalItem.getPrice();
		additional.setTotalPrice(additional.getDays() * price);
			
		orderedAdditionalItemRepository.save(additional);
		return new SuccessResult("ADDITIONAL.ADDED");
	}

	@Override
	public Result delete(DeleteOrderedAdditionalItemRequest deleteOrderedAdditionalItemRequest) {
		checkIfOrderedAdditionalItem(deleteOrderedAdditionalItemRequest.getId());
		orderedAdditionalItemRepository.deleteById(deleteOrderedAdditionalItemRequest.getId());
		return new SuccessResult("ADDITIONAL.DELETED");
	}

	@Override
	public Result update(UpdateOrderedAdditionalItemRequest updateOrderedAdditionalItemRequest) {
		checkIfOrderedAdditionalItem(updateOrderedAdditionalItemRequest.getId());
		AdditionalItem additionalItem = additionalItemService.getByAdditionalItemId(updateOrderedAdditionalItemRequest.getAdditionalItemId());
		Rental rental = rentalService.getByRentalId(updateOrderedAdditionalItemRequest.getRentalId());
		
		OrderedAdditionalItem additional = modelMapperService.forRequest().map(updateOrderedAdditionalItemRequest, OrderedAdditionalItem.class);
		
		
		additional.setDays(rental.getTotalDays());
		
		additional.setTotalPrice(additional.getDays() * additionalItem.getPrice());		
		
		orderedAdditionalItemRepository.save(additional);
		return new SuccessResult("ADDITIONAL.UPDATED");
	}

	@Override
	public DataResult<List<GetAllOrderedAdditionalItemsResponse>> getAll() {
		List<OrderedAdditionalItem> additionals = this.orderedAdditionalItemRepository.findAll();
		List<GetAllOrderedAdditionalItemsResponse> response = additionals.stream().map(additional -> this.modelMapperService.forResponse()
				.map(additional, GetAllOrderedAdditionalItemsResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllOrderedAdditionalItemsResponse>>(response,"ADDITIONALS.LISTED");
	}

	@Override
	public DataResult<GetByIdOrderedAdditionalItemResponse> getById(int id) {
		checkIfOrderedAdditionalItem(id);
		OrderedAdditionalItem orderedAdditionalItem = orderedAdditionalItemRepository.findById(id);
		GetByIdOrderedAdditionalItemResponse response = modelMapperService.forResponse().map(orderedAdditionalItem, GetByIdOrderedAdditionalItemResponse.class);
		
		return new SuccessDataResult<GetByIdOrderedAdditionalItemResponse>(response);
	}
	
	@Override
	public OrderedAdditionalItem getByOrderedAdditionalItemId(int id) {
		checkIfOrderedAdditionalItem(id);
		return orderedAdditionalItemRepository.findById(id);
	}
	
	private void checkIfOrderedAdditionalItem(int id) {
		OrderedAdditionalItem orderedAdditionalItem = orderedAdditionalItemRepository.findById(id);
		if(orderedAdditionalItem == null) throw new BusinessException("ORDERED.ADDITIONAL.ITEM.NOT.FOUND");
	}
	
//	private void checkIfAdditionalItem(int id) {
//		AdditionalItem additionalItem = additionalItemRepository.findById(id);
//		if(additionalItem == null) throw new BusinessException("ADDITIONAL.ITEM.NOT.FOUND!!!");
//	}

}
