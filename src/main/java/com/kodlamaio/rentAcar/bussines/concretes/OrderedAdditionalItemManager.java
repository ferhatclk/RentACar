package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.OrderedAdditionalItemService;
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
import com.kodlamaio.rentAcar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.OrderedAdditionalItemRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentAcar.entities.concretes.AdditionalItem;
import com.kodlamaio.rentAcar.entities.concretes.OrderedAdditionalItem;
import com.kodlamaio.rentAcar.entities.concretes.Rental;

@Service
public class OrderedAdditionalItemManager implements OrderedAdditionalItemService{
	
	private ModelMapperService modelMapperService;

	private OrderedAdditionalItemRepository orderedAdditionalItemRepository;

	private RentalRepository rentalRepository;

	private AdditionalItemRepository additionalItemRepository;

	@Autowired
	public OrderedAdditionalItemManager(ModelMapperService modelMapperService, OrderedAdditionalItemRepository orderedAdditionalItemRepository,
			RentalRepository rentalRepository, AdditionalItemRepository additionalItemRepository) {
		this.modelMapperService = modelMapperService;
		this.orderedAdditionalItemRepository = orderedAdditionalItemRepository;
		this.rentalRepository = rentalRepository;
		this.additionalItemRepository = additionalItemRepository;
	}

	@Override
	public Result add(CreateOrderedAdditionalItemRequest createAdditionalRequest) {
		checkIfAdditionalItem(createAdditionalRequest.getAdditionalItemId());
		OrderedAdditionalItem additional = modelMapperService.forRequest().map(createAdditionalRequest, OrderedAdditionalItem.class);
		Rental rental = rentalRepository.findById(createAdditionalRequest.getRentalId());
		additional.setDays(rental.getTotalDays());

		AdditionalItem additionalItem = additionalItemRepository.findById(createAdditionalRequest.getAdditionalItemId());
		
		double price = additionalItem.getPrice();
		additional.setTotalPrice(additional.getDays() * price);
			
		orderedAdditionalItemRepository.save(additional);
		return new SuccessResult("ADDITIONAL.ADDED");
	}

	@Override
	public Result delete(DeleteOrderedAdditionalItemRequest deleteAdditionalRequest) {
		orderedAdditionalItemRepository.deleteById(deleteAdditionalRequest.getId());
		return new SuccessResult("ADDITIONAL.DELETED");
	}

	@Override
	public Result update(UpdateOrderedAdditionalItemRequest updateAdditionalRequest) {
		checkIfAdditionalItem(updateAdditionalRequest.getAdditionalItemId());
		AdditionalItem additionalItem = additionalItemRepository.findById(updateAdditionalRequest.getAdditionalItemId());
		OrderedAdditionalItem additional = modelMapperService.forRequest().map(updateAdditionalRequest, OrderedAdditionalItem.class);
		Rental rental = rentalRepository.findById(updateAdditionalRequest.getRentalId());
		
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
		OrderedAdditionalItem additional = orderedAdditionalItemRepository.findById(id);
		GetByIdOrderedAdditionalItemResponse response = modelMapperService.forResponse().map(additional, GetByIdOrderedAdditionalItemResponse.class);
		
		return new SuccessDataResult<GetByIdOrderedAdditionalItemResponse>(response);
	}
	
	@Override
	public OrderedAdditionalItem getByOrderedAdditionalItemId(int id) {
		checkIfAdditionalItem(id);
		return orderedAdditionalItemRepository.findById(id);
	}
	
	private void checkIfAdditionalItem(int id) {
		AdditionalItem additionalItem = additionalItemRepository.findById(id);
		if(additionalItem == null) throw new BusinessException("ADDITIONAL.ITEM.NOT.FOUND!!!");
	}

}
