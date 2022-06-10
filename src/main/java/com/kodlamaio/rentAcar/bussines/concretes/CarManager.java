package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.CarService;
import com.kodlamaio.rentAcar.bussines.request.cars.CreateCarRequest;
import com.kodlamaio.rentAcar.bussines.request.cars.DeleteCarRequest;
import com.kodlamaio.rentAcar.bussines.request.cars.UpdateCarRequest;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.ErrorResult;
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
	
	@Autowired
	public CarManager(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	@Override
	public Result add(CreateCarRequest createCarRequest) {
		if(ifCheckExist(createCarRequest.getBrandId())<5) {
			Car car = new Car();
			car.setDailyPrice(createCarRequest.getDailyPrice());
			car.setDescription(createCarRequest.getDescription());
			car.setPlate(createCarRequest.getPlate());
			car.setKm(createCarRequest.getKm());
			car.setState(1);
			
			Brand brand = new Brand();
			brand.setId(createCarRequest.getBrandId());
			car.setBrand(brand);
			
			Color color = new Color();
			color.setId(createCarRequest.getColorId());
			car.setColor(color);
			this.carRepository.save(car);
			return new SuccessResult("CAR.ADDED");
		}else {
			return new ErrorResult("COULD.NOT.ADD.CAR!!!");
		}
		
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		carRepository.deleteById(deleteCarRequest.getId());
		return new SuccessResult("CAR.DELETE");
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Car car = carRepository.findById(updateCarRequest.getId());
		car.setDescription(updateCarRequest.getDescription());
		car.setDailyPrice(updateCarRequest.getDailyPrice());
		car.setPlate(updateCarRequest.getPlate());
		car.setKm(updateCarRequest.getKm());
		
		Brand brand = new Brand();
		brand.setId(updateCarRequest.getBrandId());
		car.setBrand(brand);
		
		Color color = new Color();
		color.setId(updateCarRequest.getColorId());
		car.setColor(color);
		
		carRepository.save(car);
		
		return new SuccessResult("CAR.UPDATE");
	}

	@Override
	public DataResult<List<Car>> getAll() {
		
		return new SuccessDataResult<List<Car>>(carRepository.findAll(),"CAR.LİSTED");
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
