package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.cars.CreateCarRequest;
import com.kodlamaio.rentAcar.bussines.request.cars.DeleteCarRequest;
import com.kodlamaio.rentAcar.bussines.request.cars.UpdateCarRequest;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.entities.concretes.Car;

public interface CarService {
	Result add(CreateCarRequest createCarRequest);
	
	Result delete(DeleteCarRequest deleteCarRequest);
	
	Result update(UpdateCarRequest updateCarRequest);
	
	DataResult<List<Car>> getAll();
	
	DataResult<Car> getById(int id);
}
