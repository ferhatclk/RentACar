package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.cars.CreateCarRequest;
import com.kodlamaio.rentAcar.bussines.request.cars.DeleteCarRequest;
import com.kodlamaio.rentAcar.bussines.request.cars.UpdateCarRequest;
import com.kodlamaio.rentAcar.bussines.response.cars.GetAllCarsResponse;
import com.kodlamaio.rentAcar.bussines.response.cars.GetByIdCarResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface CarService {
	Result add(CreateCarRequest createCarRequest);
	
	Result delete(DeleteCarRequest deleteCarRequest);
	
	Result update(UpdateCarRequest updateCarRequest);
	
	DataResult<List<GetAllCarsResponse>> getAll();
	
	DataResult<GetByIdCarResponse> getById(int id);
}
