package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.additionalItems.CreateAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.additionalItems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.response.additionalItems.GetAllAdditionalItemsResponse;
import com.kodlamaio.rentAcar.bussines.response.additionalItems.GetByIdAdditionalItemResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface AdditionalItemService {
	Result add(CreateAdditionalItemRequest createAdditionalItemRequest);
	
	Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest);
	
	Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest);
	
	DataResult<List<GetAllAdditionalItemsResponse>> getAll();
	
	DataResult<GetByIdAdditionalItemResponse> getById(int id);
}
