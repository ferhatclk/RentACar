package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.rentals.CreateRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.DeleteRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.UpdateRentalRequest;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetRentalResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface RentalService {
	Result add(CreateRentalRequest createRentalRequest);
	Result delete(DeleteRentalRequest deleteRentalRequest);
	Result update(UpdateRentalRequest updateRentalRequest);
	DataResult<List<GetAllRentalsResponse>> getAll();
	DataResult<GetRentalResponse> getById(int id);
}
