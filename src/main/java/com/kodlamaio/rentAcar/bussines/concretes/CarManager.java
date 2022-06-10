package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.CarService;
import com.kodlamaio.rentAcar.bussines.request.cars.CreateCarRequest;
import com.kodlamaio.rentAcar.bussines.request.cars.DeleteCarRequest;
import com.kodlamaio.rentAcar.bussines.request.cars.UpdateCarRequest;
import com.kodlamaio.rentAcar.bussines.response.cars.GetAllCarsResponse;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.ErrorResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentAcar.entities.concretes.Car;

@Service
public class CarManager implements CarService{
	
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarRepository carRepository,ModelMapperService modelMapperService) {
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public Result add(CreateCarRequest createCarRequest) {
		if(ifCheckExist(createCarRequest.getBrandId())<5) {
			Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
			car.setState(1);
			this.carRepository.save(car);
			return new SuccessResult("CAR.ADDED");
		}else {
			return new ErrorResult("COULD.NOT.ADD.CAR!!!");
		}
		
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		Car car = carRepository.findById(deleteCarRequest.getId());
		if(car.getState()==1) {
			carRepository.deleteById(deleteCarRequest.getId());
			return new SuccessResult("CAR.DELETE");
		}else {
			return new ErrorResult("COULD.NOT.DELETE!!!");
		}
		
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		car.setState(1);
		carRepository.save(car);
		return new SuccessResult("CAR.UPDATE");
	}

	@Override
	public DataResult<List<GetAllCarsResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> response = cars.stream().map(car -> this.modelMapperService.forResponse()
				.map(car, GetAllCarsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCarsResponse>>(response);
	}

	@Override
	public DataResult<Car> getById(int id) {
		
		return new SuccessDataResult<Car>(this.carRepository.findById(id));
	}
	
	private int ifCheckExist(int id) {
		int count = 0;
		for(Car item: carRepository.findAll()) {
			if(item.getBrand().getId()==id) {
				count++;
			}
		}
		return count;
	}

}
