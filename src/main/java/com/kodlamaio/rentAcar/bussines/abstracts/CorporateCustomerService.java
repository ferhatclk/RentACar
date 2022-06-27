package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.corporateCustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.corporateCustomers.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.corporateCustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.bussines.response.corporateCustomers.GetAllCorporateCustomersResponse;
import com.kodlamaio.rentAcar.bussines.response.corporateCustomers.GetByIdCorporateCustomerResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface CorporateCustomerService {
	
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequet);
	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	DataResult<List<GetAllCorporateCustomersResponse>> getAll();
	DataResult<GetByIdCorporateCustomerResponse> getById(int id);
}
