package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.RentalService;
import com.kodlamaio.rentAcar.bussines.request.rentals.CreateRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.DeleteRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.UpdateRentalRequest;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetRentalResponse;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentAcar.entities.concretes.Car;
import com.kodlamaio.rentAcar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService{
	@Autowired
	private RentalRepository rentalRepository;
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	
	public RentalManager(RentalRepository rentalRepository,CarRepository carRepository, ModelMapperService modelMapperService) {
		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		ifCheckState(createRentalRequest.getCarId());
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		 
		Date pickDate = createRentalRequest.getPickupDate();
		Date returnDate = createRentalRequest.getReturnDate();
		
		long totalDays = dayDifference(pickDate, returnDate);
		rental.setTotalDays(totalDays);
		
		Car car = carRepository.findById(createRentalRequest.getCarId());
//		car.setId(createRentalRequest.getCarId());
		car.setState(3);
		rental.setCar(car);
		
		double dailyPrice = car.getDailyPrice();
		int pickCity = createRentalRequest.getPickUpCityId();
		int returnCity = createRentalRequest.getReturnCityId();
		
		rental.setTotalPrice(fullPrice(totalDays, dailyPrice, pickCity, returnCity));
		rentalRepository.save(rental);

		return new SuccessResult("RENTAL.ADDED");
		
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		
		Rental rental = rentalRepository.findById(deleteRentalRequest.getId());
		
		Car car = rental.getCar();
		car.setState(1);
		carRepository.save(car);

		rentalRepository.delete(rental);
		return new SuccessResult("RENTAL.DELETED");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		
		ifCheckState(updateRentalRequest.getCarId());
		stateCar(updateRentalRequest.getId());
		
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		
		Car car = carRepository.findById(updateRentalRequest.getCarId());
		car.setState(3);
		rental.setCar(car);
		
		Date pickDate = updateRentalRequest.getPickupDate();
		Date returnDate = updateRentalRequest.getReturnDate();
		
		long totalDays = dayDifference(pickDate, returnDate);
		rental.setTotalDays(totalDays);
		
		int pickCity = updateRentalRequest.getPickUpCityId();
		int returnCity = updateRentalRequest.getReturnCityId();
	
		double dailyPrice = car.getDailyPrice();
		rental.setTotalPrice(fullPrice(totalDays, dailyPrice,  pickCity, returnCity));
		
		rentalRepository.save(rental);
		
		return new SuccessResult("RENTAL.UPDATED");
		
	}

	@Override
	public DataResult<List<GetAllRentalsResponse>> getAll() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsResponse> response = rentals.stream().map(rental -> this.modelMapperService.forResponse()
				.map(rental, GetAllRentalsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRentalsResponse>>(response);
	}

	@Override
	public DataResult<GetRentalResponse> getById(int id) {
		Rental rental = this.rentalRepository.findById(id);
		GetRentalResponse response = this.modelMapperService.forResponse().map(rental, GetRentalResponse.class);
		return new SuccessDataResult<GetRentalResponse>(response,"GET.BY.ID.RENTAL");
	}
	
	private void ifCheckState(int id) {
		Car car = carRepository.findById(id);
		if(car.getState()==2) {
			throw new BusinessException("STATE.DOES.NOT.FIT!!!!");
		}
	}
	
	private void stateCar(int id) {
		Rental rental = rentalRepository.findById(id);
		Car car = rental.getCar();
		car.setState(1);
	}
	
	private long dayDifference(Date datePickup, Date dateReturned) {
		long dif = ((dateReturned.getTime() - datePickup.getTime())/(1000*60*60*24));
		return dif;
	}
	
	private double fullPrice(long day, double dailyPrice, int pickId, int returnId) {
		if(pickId == returnId) {
			double fullprice = day * dailyPrice;
			return fullprice;
		}else {
			double fullprice = (day * dailyPrice) + 750;
			return fullprice;
		}
	}
	
}
