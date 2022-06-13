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
import com.kodlamaio.rentAcar.dataAccess.abstracts.AdditionalRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentAcar.entities.concretes.Additional;

@Service
public class AdditionalManager implements AdditionalService{
	@Autowired
	private ModelMapperService modelMapperService;
	private AdditionalRepository additionalRepository;
	private RentalRepository rentalRepository;

	public AdditionalManager(ModelMapperService modelMapperService, AdditionalRepository additionalRepository, RentalRepository rentalRepository) {
		this.modelMapperService = modelMapperService;
		this.additionalRepository = additionalRepository;
		this.rentalRepository = rentalRepository;
	}

	@Override
	public Result add(CreateAdditionalRequest createAdditionalRequest) {
		Additional additional = modelMapperService.forRequest().map(createAdditionalRequest, Additional.class);
//		Rental rental = rentalRepository.findById(createAdditionalRequest.getRentalId());
//		rental.set;
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
		Additional additional = modelMapperService.forRequest().map(updateAdditionalRequest, Additional.class);
		additionalRepository.save(additional);
		return new SuccessResult("ADDITIONAL.UPDATED");
	}

	@Override
	public DataResult<List<GetAllAdditionalsResponse>> getAll() {
		List<Additional> additionals = this.additionalRepository.findAll();
		List<GetAllAdditionalsResponse> response = additionals.stream().map(additional -> this.modelMapperService.forResponse()
				.map(additional, GetAllAdditionalsResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllAdditionalsResponse>>(response,"ADDITIONALS.LISTED");
	}

	@Override
	public DataResult<GetByIdAdditionalResponse> getById(int id) {
		Additional additional = additionalRepository.findById(id);
		GetByIdAdditionalResponse response = modelMapperService.forResponse().map(additional, GetByIdAdditionalResponse.class);
		return new SuccessDataResult<GetByIdAdditionalResponse>(response);
	}

}
