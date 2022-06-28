package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.rentals.CreateCorporateCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.CreateIndividualCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.DeleteCorporateCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.DeleteIndividualCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.UpdateCorporateCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.UpdateIndividualCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetRentalResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.entities.concretes.Rental;

public interface RentalService {
	Result addForIndividual(CreateIndividualCustomerRentalRequest createIndividualCustomerRentalRequest);
	Result addForCorporate(CreateCorporateCustomerRentalRequest createCorporateCustomerRentalRequest);
	
	Result deleteForIndividual(DeleteIndividualCustomerRentalRequest deleteIndividualCustomerRentalRequest);
	Result deleteForCorporate(DeleteCorporateCustomerRentalRequest deleteCorporateCustomerRentalRequest);
	
	Result updateForIndividual(UpdateIndividualCustomerRentalRequest updateIndividualCustomerRentalRequest);
	Result updateForCorporate(UpdateCorporateCustomerRentalRequest updateCorporateCustomerRentalRequest);
	
	DataResult<List<GetAllRentalsResponse>> getAll();
	DataResult<GetRentalResponse> getById(int id);
	
	Rental getByRentalId(int id);
}
