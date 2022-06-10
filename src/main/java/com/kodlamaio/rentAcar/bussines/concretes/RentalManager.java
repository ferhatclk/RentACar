package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.RentalService;
import com.kodlamaio.rentAcar.bussines.request.rentals.CreateRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.DeleteRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.UpdateRentalRequest;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.ErrorResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentAcar.entities.concretes.Car;
import com.kodlamaio.rentAcar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService{
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
		Car car = carRepository.findById(createRentalRequest.getCarId());
		if(car.getState()==1) {
			Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
			
			rental.setPickupDate(createRentalRequest.getPickupDate());
			rental.setReturnDate(createRentalRequest.getReturnDate());
			 
			
			long totalDays = dayDifference(createRentalRequest.getPickupDate(), createRentalRequest.getReturnDate());
			rental.setTotalDays(totalDays);
			
			
			car.setId(createRentalRequest.getCarId());
			
			rental.setCar(car);
			car.setState(3);
			double totalPrice = car.getDailyPrice();
			rental.setTotalPrice(fullPrice(totalDays, totalPrice));
			rentalRepository.save(rental);

			return new SuccessResult("RENTAL.ADDED");
		}else {
			return new ErrorResult("DO.NOT.ADD.RENTAL!!!!");
		}
		
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		rentalRepository.deleteById(deleteRentalRequest.getId());
		return new SuccessResult("RENTAL.DELETED");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
//		rental.setPickupDate(updateRentalRequest.getPickupDate());
//		rental.setReturnDate(updateRentalRequest.getReturnDate());
		
		long totalDays = dayDifference(updateRentalRequest.getPickupDate(), updateRentalRequest.getReturnDate());
		rental.setTotalDays(totalDays);
		
		Car car = carRepository.findById(updateRentalRequest.getCarId());
		car.setId(updateRentalRequest.getCarId());
		
		rental.setCar(car);
		
		double totalPrice = car.getDailyPrice();
		rental.setTotalPrice(fullPrice(totalDays, totalPrice));
		
		car.setState(3);
		rentalRepository.save(rental);
		
		return new SuccessResult("RENTAL.UPDATED");
	}
	
	private long dayDifference(Date datePickup, Date dateReturned) {
		long dif = ((dateReturned.getTime() - datePickup.getTime())/(1000*60*60*24));
		return dif;
	}
	
	private double fullPrice(long day, double price) {
		double fullprice = day * price;
		return fullprice;
	}

	@Override
	public DataResult<List<GetAllRentalsResponse>> getAll() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsResponse> response = rentals.stream().map(rental -> this.modelMapperService.forResponse()
				.map(rental, GetAllRentalsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRentalsResponse>>(response);
	}
	
}
