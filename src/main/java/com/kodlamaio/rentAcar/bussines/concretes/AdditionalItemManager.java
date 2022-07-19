package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.AdditionalItemService;
import com.kodlamaio.rentAcar.bussines.request.additionalItems.CreateAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.additionalItems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.response.additionalItems.GetAllAdditionalItemsResponse;
import com.kodlamaio.rentAcar.bussines.response.additionalItems.GetByIdAdditionalItemResponse;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentAcar.entities.concretes.AdditionalItem;

@Service
public class AdditionalItemManager implements AdditionalItemService{
	
	private AdditionalItemRepository additionalItemRepository;

	private ModelMapperService modelMapperService;

	@Autowired
	public AdditionalItemManager(AdditionalItemRepository additionalItemRepository, ModelMapperService modelMapperService) {
		this.additionalItemRepository = additionalItemRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdditionalItemRequest createAdditionalItemRequest) {
		AdditionalItem additionalItem = this.modelMapperService.forRequest().map(createAdditionalItemRequest, AdditionalItem.class);
		additionalItemRepository.save(additionalItem);
		return new SuccessResult("ADDITIONAL.ITEM.ADDED");
	}

	@Override
	public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		checkIfAdditionalItem(deleteAdditionalItemRequest.getId());
		additionalItemRepository.deleteById(deleteAdditionalItemRequest.getId());
		return new SuccessResult("ADDITIONAL.DELETED");
	}

	@Override
	public Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		checkIfAdditionalItem(updateAdditionalItemRequest.getId());
		AdditionalItem additionalItem = this.modelMapperService.forRequest().map(updateAdditionalItemRequest, AdditionalItem.class);
		additionalItemRepository.save(additionalItem);
		return new SuccessResult("ADDITIONAL.UPDATED");
	}

	@Override
	public DataResult<List<GetAllAdditionalItemsResponse>> getAll() {
		List<AdditionalItem> additionalItems = additionalItemRepository.findAll();
		List<GetAllAdditionalItemsResponse> response = additionalItems.stream().map(additionalItem -> this.modelMapperService.forResponse()
				.map(additionalItem, GetAllAdditionalItemsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalItemsResponse>>(response,"ADDITIONALS.LISTED");
	}

	@Override
	public DataResult<GetByIdAdditionalItemResponse> getById(int id) {
		checkIfAdditionalItem(id);
		AdditionalItem additionalItem = this.additionalItemRepository.findById(id);
		GetByIdAdditionalItemResponse response = this.modelMapperService.forResponse().map(additionalItem, GetByIdAdditionalItemResponse.class);
		return new SuccessDataResult<GetByIdAdditionalItemResponse>(response,"ADDITIONAL.LISTED.GET.BY.ID");
	}

	@Override
	public AdditionalItem getByAdditionalItemId(int id) {
		// TODO Auto-generated method stub
		return additionalItemRepository.findById(id);
	}
	
	private void checkIfAdditionalItem(int id) {
		AdditionalItem additionalItem = additionalItemRepository.findById(id);
		if(additionalItem == null) throw new BusinessException("ADDITIONAL.ITEM.NOT.FOUND");
	}

}
