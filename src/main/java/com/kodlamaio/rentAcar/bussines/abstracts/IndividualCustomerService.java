package com.kodlamaio.rentAcar.bussines.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.individualCustomers.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.individualCustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.bussines.response.individualCustomers.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentAcar.bussines.response.individualCustomers.GetByIdIndividualCustomerResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface IndividualCustomerService {
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException;;
	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	DataResult<List<GetAllIndividualCustomersResponse>> getAll();
	DataResult<List<GetAllIndividualCustomersResponse>> getAll(int pageNo, int pageSize);
	DataResult<GetByIdIndividualCustomerResponse> getById(int id);
}
