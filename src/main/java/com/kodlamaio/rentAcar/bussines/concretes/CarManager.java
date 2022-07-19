package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.BrandService;
import com.kodlamaio.rentAcar.bussines.abstracts.CarService;
import com.kodlamaio.rentAcar.bussines.abstracts.ColorService;
import com.kodlamaio.rentAcar.bussines.request.cars.CreateCarRequest;
import com.kodlamaio.rentAcar.bussines.request.cars.DeleteCarRequest;
import com.kodlamaio.rentAcar.bussines.request.cars.UpdateCarRequest;
import com.kodlamaio.rentAcar.bussines.response.cars.GetAllCarsResponse;
import com.kodlamaio.rentAcar.bussines.response.cars.GetByIdCarResponse;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentAcar.entities.concretes.Brand;
import com.kodlamaio.rentAcar.entities.concretes.Car;
import com.kodlamaio.rentAcar.entities.concretes.Color;

@Service
public class CarManager implements CarService{
	
	private CarRepository carRepository;

	private ModelMapperService modelMapperService;
	
	private BrandService brandService;
	
	private ColorService colorService;
	
	@Autowired
	public CarManager(CarRepository carRepository,ModelMapperService modelMapperService,
			BrandService brandService, ColorService colorService) {
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
		this.brandService = brandService;
		this.colorService = colorService;
	}
	
	@Override
	public Result add(CreateCarRequest createCarRequest) {
		checkIfBrand(createCarRequest.getBrandId());
		checkIfExistCount(createCarRequest.getBrandId());
		checkIfColor(createCarRequest.getColorId());
//		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		Car car = Car.builder()
				.description(createCarRequest.getDescription())
				.dailyPrice(createCarRequest.getDailyPrice())
				.findexScore(createCarRequest.getFindexScore())
				.km(createCarRequest.getKm())
				.plate(createCarRequest.getPlate())
				.brand(brandService.getByBrandId(createCarRequest.getBrandId()))
				.color(colorService.getByColorId(createCarRequest.getColorId()))
				.state(1)
				.build();
//		car.setState(1);
		this.carRepository.save(car);
		return new SuccessResult("CAR.ADDED");
		
		
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		checkIfCar(deleteCarRequest.getId());
		Car car = carRepository.findById(deleteCarRequest.getId());
		checkIfCarState(car.getState());
		carRepository.deleteById(deleteCarRequest.getId());
		return new SuccessResult("CAR.DELETE");
		
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		checkIfCar(updateCarRequest.getId());
		checkIfExistCount(updateCarRequest.getBrandId());
		checkIfColor(updateCarRequest.getColorId());
		checkIfBrand(updateCarRequest.getBrandId());
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		car.setState(1);
		carRepository.save(car);
		return new SuccessResult("CAR.UPDATE");
	}
	
//	@Cacheable("cars")
	@Override
	public DataResult<List<GetAllCarsResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> response = cars.stream().map(car -> this.modelMapperService.forResponse()
				.map(car, GetAllCarsResponse.class)).collect(Collectors.toList());
		try {
			Thread.sleep(1000 * 4);
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
		
		return new SuccessDataResult<List<GetAllCarsResponse>>(response);
	}

	@Override
	public DataResult<GetByIdCarResponse> getById(int id) {
		Car car = this.carRepository.findById(id);
		GetByIdCarResponse response = this.modelMapperService.forResponse().map(car, GetByIdCarResponse.class);
		return new SuccessDataResult<GetByIdCarResponse>(response,"CAR.GET.ID");
	}
	
	@Override
	public Car getByCarId(int id) {
		checkIfCar(id);
		return carRepository.findById(id);
	}
	
	private void checkIfExistCount(int id) {
		List<Car> cars = carRepository.findByBrandId(id);
		if(cars.size() > 4) {
			throw new BusinessException("CAR.EXIST");
		}
	}
	
	private void checkIfBrand(int id) {
		Brand brand = brandService.getByBrandId(id);
		if(brand == null) {
			throw new BusinessException("BRAND.NOT.FOUND!!!");
		}
	}
	
	private void checkIfColor(int id) {
		Color color = colorService.getByColorId(id);
		if(color == null) throw new BusinessException("BRAND.NOT.FOUND!!!");
	}
	
	private void checkIfCar(int id) {
		Car car = carRepository.findById(id);
		if(car == null) throw new BusinessException("CAR.NOT.FOUND!!");
	}
	
	private void checkIfCarState(int state) {
		if(state !=1) {
			throw new BusinessException("CAR.NOT.DELETE!!!");
		}
	}

}
