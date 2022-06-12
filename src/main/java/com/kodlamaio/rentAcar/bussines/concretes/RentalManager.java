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
		Car car = carRepository.findById(createRentalRequest.getCarId());
		if(car.getState()==1) {
			Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
			 
			Date pickDate = createRentalRequest.getPickupDate();
			Date returnDate = createRentalRequest.getReturnDate();
			
			long totalDays = dayDifference(pickDate, returnDate);
			rental.setTotalDays(totalDays);
			
			
			car.setId(createRentalRequest.getCarId());
			car.setState(3);
			rental.setCar(car);
			
			double totalPrice = car.getDailyPrice();
			int pickCity = createRentalRequest.getPickUpCityId();
			int returnCity = createRentalRequest.getReturnCityId();
			
			rental.setTotalPrice(fullPrice(totalDays, totalPrice, pickCity, returnCity));
			rentalRepository.save(rental);

			return new SuccessResult("RENTAL.ADDED");
		}else {
			return new ErrorResult("DO.NOT.ADD.RENTAL!!!!");
		}
		
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Car car = carRepository.findById(deleteRentalRequest.getCarId());
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		rental.setCar(car);
		car.setState(1);

//		rentalRepository.save(rental);
		rentalRepository.deleteById(deleteRentalRequest.getId());
		return new SuccessResult("RENTAL.DELETED");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Car car = carRepository.findById(updateRentalRequest.getCarId());
		if(car.getState()==1) {
			Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
			
			Date pickDate = updateRentalRequest.getPickupDate();
			Date returnDate = updateRentalRequest.getReturnDate();
			
			long totalDays = dayDifference(pickDate, returnDate);
			rental.setTotalDays(totalDays);
			
			//car.setId(updateRentalRequest.getCarId());
			car.setState(3);
			rental.setCar(car);
			
			
			int pickCity = updateRentalRequest.getPickUpCityId();
			int returnCity = updateRentalRequest.getReturnCityId();
		
			double totalPrice = car.getDailyPrice();
			rental.setTotalPrice(fullPrice(totalDays, totalPrice,  pickCity, returnCity));
			
			
			rentalRepository.save(rental);
			
			return new SuccessResult("RENTAL.UPDATED");
		}else {
			return new ErrorResult("DO.NOT.UPDATED.RENTAL!!!!");
		}
		
	}
//	private Result stateCar(CreateRentalRequest createRentalRequest) {
//		Car car = carRepository.findById(createRentalRequest.getCarId());
//		
//		for (Rental item : rentalRepository.findAll()) {
//			if(item.getCar()!=car) {
//				car.setState(1);
//			}
//		}
//		
//		return new SuccessResult("UPDATE.RENTAL.STATE.UPDATE2");
//	}
	
	private long dayDifference(Date datePickup, Date dateReturned) {
		long dif = ((dateReturned.getTime() - datePickup.getTime())/(1000*60*60*24));
		return dif;
	}
	
	private double fullPrice(long day, double price, int pickId, int returnId) {
		if(pickId == returnId) {
			double fullprice = day * price;
			return fullprice;
		}else {
			double fullprice = (day * price) + 750;
			return fullprice;
		}
		
		
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
	
}
