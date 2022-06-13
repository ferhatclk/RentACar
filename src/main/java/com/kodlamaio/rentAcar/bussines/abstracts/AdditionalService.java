package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.additionals.CreateAdditionalRequest;
import com.kodlamaio.rentAcar.bussines.request.additionals.DeleteAdditionalRequest;
import com.kodlamaio.rentAcar.bussines.request.additionals.UpdateAdditionalRequest;
import com.kodlamaio.rentAcar.bussines.response.additionals.GetAllAdditionalsResponse;
import com.kodlamaio.rentAcar.bussines.response.additionals.GetByIdAdditionalResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface AdditionalService {
	Result add(CreateAdditionalRequest createAdditionalRequest);
	Result delete(DeleteAdditionalRequest deleteAdditionalRequest);
	Result update(UpdateAdditionalRequest updateAdditionalRequest);
	DataResult<List<GetAllAdditionalsResponse>> getAll();
	DataResult<GetByIdAdditionalResponse> getById(int id);
}
