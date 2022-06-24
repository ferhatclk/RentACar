package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.AdditionalService;
import com.kodlamaio.rentAcar.bussines.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentAcar.bussines.request.additionals.DeleteAdditionalRequest;
import com.kodlamaio.rentAcar.bussines.request.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentAcar.bussines.response.additionals.GetAllAdditionalsResponse;
import com.kodlamaio.rentAcar.bussines.response.additionals.GetByIdAdditionalResponse;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.AdditionalRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentAcar.entities.concretes.OrderedAdditionalItem;
import com.kodlamaio.rentAcar.entities.concretes.AdditionalItem;
import com.kodlamaio.rentAcar.entities.concretes.Rental;

@Service
public class AdditionalManager implements AdditionalService{
	
	private ModelMapperService modelMapperService;

	private AdditionalRepository additionalRepository;

	private RentalRepository rentalRepository;

	private AdditionalItemRepository additionalItemRepository;

	@Autowired
	public AdditionalManager(ModelMapperService modelMapperService, AdditionalRepository additionalRepository,
			RentalRepository rentalRepository, AdditionalItemRepository additionalItemRepository) {
		this.modelMapperService = modelMapperService;
		this.additionalRepository = additionalRepository;
		this.rentalRepository = rentalRepository;
		this.additionalItemRepository = additionalItemRepository;
	}

	@Override
	public Result add(CreateAdditionalRequest createAdditionalRequest) {
		OrderedAdditionalItem additional = modelMapperService.forRequest().map(createAdditionalRequest, OrderedAdditionalItem.class);
		Rental rental = rentalRepository.findById(createAdditionalRequest.getRentalId());
		additional.setDays(rental.getTotalDays());

		AdditionalItem additionalItem = additionalItemRepository.findById(createAdditionalRequest.getAdditionalItemId());
		
		double price = additionalItem.getPrice();
		additional.setTotalPrice(additional.getDays() * price);
			
		additionalRepository.save(additional);
		return new SuccessResult("ADDITIONAL.ADDED");
	}

	@Override
	public Result delete(DeleteAdditionalRequest deleteAdditionalRequest) {
		additionalRepository.deleteById(deleteAdditionalRequest.getId());
		return new SuccessResult("ADDITIONAL.DELETED");
	}

	@Override
	public Result update(UpdateAdditionalRequest updateAdditionalRequest) {
		AdditionalItem additionalItem = additionalItemRepository.findById(updateAdditionalRequest.getAdditionalItemId());
		OrderedAdditionalItem additional = modelMapperService.forRequest().map(updateAdditionalRequest, OrderedAdditionalItem.class);
		Rental rental = rentalRepository.findById(updateAdditionalRequest.getRentalId());
		
		additional.setDays(rental.getTotalDays());
		
		additional.setTotalPrice(additional.getDays() * additionalItem.getPrice());		
		
		additionalRepository.save(additional);
		return new SuccessResult("ADDITIONAL.UPDATED");
	}

	@Override
	public DataResult<List<GetAllAdditionalsResponse>> getAll() {
		List<OrderedAdditionalItem> additionals = this.additionalRepository.findAll();
		List<GetAllAdditionalsResponse> response = additionals.stream().map(additional -> this.modelMapperService.forResponse()
				.map(additional, GetAllAdditionalsResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllAdditionalsResponse>>(response,"ADDITIONALS.LISTED");
	}

	@Override
	public DataResult<GetByIdAdditionalResponse> getById(int id) {
		OrderedAdditionalItem additional = additionalRepository.findById(id);
		GetByIdAdditionalResponse response = modelMapperService.forResponse().map(additional, GetByIdAdditionalResponse.class);
		
		return new SuccessDataResult<GetByIdAdditionalResponse>(response);
	}

}
