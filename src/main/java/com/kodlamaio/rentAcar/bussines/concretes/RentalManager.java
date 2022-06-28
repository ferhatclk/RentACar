package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.CarService;
import com.kodlamaio.rentAcar.bussines.abstracts.CorporateCustomerService;
import com.kodlamaio.rentAcar.bussines.abstracts.IndividualCustomerService;
import com.kodlamaio.rentAcar.bussines.abstracts.RentalService;
import com.kodlamaio.rentAcar.bussines.request.rentals.CreateCorporateCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.CreateIndividualCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.DeleteCorporateCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.DeleteIndividualCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.UpdateCorporateCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.request.rentals.UpdateIndividualCustomerRentalRequest;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentAcar.bussines.response.rentals.GetRentalResponse;
import com.kodlamaio.rentAcar.core.services.findex.FindexCheckService;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentAcar.entities.concretes.Car;
import com.kodlamaio.rentAcar.entities.concretes.CorporateCustomer;
import com.kodlamaio.rentAcar.entities.concretes.IndividualCustomer;
import com.kodlamaio.rentAcar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	private RentalRepository rentalRepository;
	private CarService carService;
	private ModelMapperService modelMapperService;
	private FindexCheckService findexCheckService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;

// bir service sadece kendi repositorysini enjekte edebilir	
// lazy anotasyonu
	@Autowired
	public RentalManager(RentalRepository rentalRepository, ModelMapperService modelMapperService,
			CarService carService, FindexCheckService findexCheckService,
			IndividualCustomerService individualCustomerService,CorporateCustomerService corporateCustomerService) {
		
		this.corporateCustomerService = corporateCustomerService;
		this.rentalRepository = rentalRepository;
		this.carService = carService;
		this.modelMapperService = modelMapperService;
		this.findexCheckService = findexCheckService;
		this.individualCustomerService = individualCustomerService;

	}

	@Override
	public Result addForIndividual(CreateIndividualCustomerRentalRequest createIndividualCustomerRentalRequest) {
		checkIfCar(createIndividualCustomerRentalRequest.getCarId());
		checkIfState(createIndividualCustomerRentalRequest.getCarId());
		checkIfDate(createIndividualCustomerRentalRequest.getPickupDate(), createIndividualCustomerRentalRequest.getReturnDate());
		checkIfIndividualCustomer(createIndividualCustomerRentalRequest.getCustomerId());
		Rental rental = this.modelMapperService.forRequest().map(createIndividualCustomerRentalRequest, Rental.class);

		Date pickupDate = createIndividualCustomerRentalRequest.getPickupDate();
		Date returnDate = createIndividualCustomerRentalRequest.getReturnDate();

		long totalDays = dayDifference(pickupDate, returnDate);
		rental.setTotalDays(totalDays);

		Car car = carService.getByCarId(createIndividualCustomerRentalRequest.getCarId());
		IndividualCustomer individualCustomer = individualCustomerService.getByCustomerId(createIndividualCustomerRentalRequest.getCustomerId());

		ifEqualFindexScore(car.getFindexScore(), individualCustomer.getNationalIdentity());
		car.setState(3);
		rental.setCar(car);

		double dailyPrice = car.getDailyPrice();
		int pickupCity = createIndividualCustomerRentalRequest.getPickUpCityId();
		int returnCity = createIndividualCustomerRentalRequest.getReturnCityId();

		rental.setTotalPrice(fullPrice(totalDays, dailyPrice, pickupCity, returnCity));
		rentalRepository.save(rental);
		return new SuccessResult("RENTAL.ADDED.FOR.INDIVIDUAL.CUSTOMER");

	}

	@Override
	public Result addForCorporate(CreateCorporateCustomerRentalRequest createCorporateCustomerRentalRequest) {
		checkIfCar(createCorporateCustomerRentalRequest.getCarId());
		checkIfState(createCorporateCustomerRentalRequest.getCarId());
		checkIfDate(createCorporateCustomerRentalRequest.getPickupDate(), createCorporateCustomerRentalRequest.getReturnDate());
		checkIfCorporateCustomer(createCorporateCustomerRentalRequest.getCustomerId());
		Rental rental = modelMapperService.forRequest().map(createCorporateCustomerRentalRequest, Rental.class);

		Date pickupDate = createCorporateCustomerRentalRequest.getPickupDate();
		Date returnDate = createCorporateCustomerRentalRequest.getReturnDate();

		long totalDays = dayDifference(pickupDate, returnDate);
		rental.setTotalDays(totalDays);
		
		
		Car car = carService.getByCarId(createCorporateCustomerRentalRequest.getCarId());

//		CorporateCustomer corporateCustomer = comporateCustomerRepository.findById(createRentalRequest.getCustomerId());
//		ifEqualFindexScore(car.getFindexScore(), corporateCustomer.getCorporateCustomerTaxNumber());
		car.setState(3);
		rental.setCar(car);

		double dailyPrice = car.getDailyPrice();
		int pickCity = createCorporateCustomerRentalRequest.getPickupCityId();
		int returnCity = createCorporateCustomerRentalRequest.getReturnCityId();

		rental.setTotalPrice(fullPrice(totalDays, dailyPrice, pickCity, returnCity));
		rentalRepository.save(rental);
		return new SuccessResult("RENTAL.ADDED.FOR.CORPORATE.CUSTOMER");
	}

	@Override
	public Result deleteForCorporate(DeleteCorporateCustomerRentalRequest deleteCorporateCustomerRentalRequest) {

		Rental rental = rentalRepository.findById(deleteCorporateCustomerRentalRequest.getId());
		Car car = rental.getCar();
		car.setState(1);

		rentalRepository.delete(rental);
		return new SuccessResult("CORPORATE.RENTAL.DELETED");
	}

	@Override
	public Result deleteForIndividual(DeleteIndividualCustomerRentalRequest deleteIndividualCustomerRentalRequest) {
		Rental rental = rentalRepository.findById(deleteIndividualCustomerRentalRequest.getId());
		Car car = carService.getByCarId(rental.getCar().getId());
		car.setState(1);

		rentalRepository.delete(rental);
		return new SuccessResult("INDIVIDUAL.RENTAL.DELETED");
	}

	@Override
	public Result updateForIndividual(UpdateIndividualCustomerRentalRequest updateIndividualCustomerRentalRequest) {
		checkIfRental(updateIndividualCustomerRentalRequest.getId());
		checkIfIndividualCustomer(updateIndividualCustomerRentalRequest.getCustomerId());
		checkIfState(updateIndividualCustomerRentalRequest.getCarId());
		stateCar(updateIndividualCustomerRentalRequest.getId());
		checkIfDate(updateIndividualCustomerRentalRequest.getPickupDate(), updateIndividualCustomerRentalRequest.getReturnDate());
		Rental rental = this.modelMapperService.forRequest().map(updateIndividualCustomerRentalRequest, Rental.class);

		Car car = carService.getByCarId(updateIndividualCustomerRentalRequest.getCarId());
		car.setState(3);
		rental.setCar(car);

		IndividualCustomer individualCustomer = individualCustomerService
				.getByCustomerId(updateIndividualCustomerRentalRequest.getCustomerId());
		ifEqualFindexScore(car.getFindexScore(), individualCustomer.getNationalIdentity());

		Date pickDate = updateIndividualCustomerRentalRequest.getPickupDate();
		Date returnDate = updateIndividualCustomerRentalRequest.getReturnDate();

		long totalDays = dayDifference(pickDate, returnDate);
		rental.setTotalDays(totalDays);

		int pickCity = updateIndividualCustomerRentalRequest.getPickUpCityId();
		int returnCity = updateIndividualCustomerRentalRequest.getReturnCityId();

		double dailyPrice = car.getDailyPrice();
		rental.setTotalPrice(fullPrice(totalDays, dailyPrice, pickCity, returnCity));

		rentalRepository.save(rental);

		return new SuccessResult("RENTAL.UPDATED.FOR.INDIVIDUAL");

	}

	@Override
	public Result updateForCorporate(UpdateCorporateCustomerRentalRequest updateCorporateCustomerRentalRequest) {
		checkIfRental(updateCorporateCustomerRentalRequest.getId());
		checkIfCorporateCustomer(updateCorporateCustomerRentalRequest.getCustomerId());
		checkIfState(updateCorporateCustomerRentalRequest.getCarId());
		stateCar(updateCorporateCustomerRentalRequest.getId());
		checkIfDate(updateCorporateCustomerRentalRequest.getPickupDate(), updateCorporateCustomerRentalRequest.getReturnDate());
		Rental rental = this.modelMapperService.forRequest().map(updateCorporateCustomerRentalRequest, Rental.class);

		Car car = carService.getByCarId(updateCorporateCustomerRentalRequest.getCarId());
		car.setState(3);
		rental.setCar(car);

		Date pickupDate = updateCorporateCustomerRentalRequest.getPickupDate();
		Date returnDate = updateCorporateCustomerRentalRequest.getReturnDate();

		int totalDays = dayDifference(pickupDate, returnDate);
		rental.setTotalDays(totalDays);

		int pickupCity = updateCorporateCustomerRentalRequest.getPickUpCityId();
		int returnCity = updateCorporateCustomerRentalRequest.getReturnCityId();

		double dailyPrice = car.getDailyPrice();
		rental.setTotalPrice(fullPrice(totalDays, dailyPrice, pickupCity, returnCity));

		rentalRepository.save(rental);

		return new SuccessResult("RENTAL.UPDATED.FOR.CORPORATE");

	}

	@Override
	public DataResult<List<GetAllRentalsResponse>> getAll() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllRentalsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRentalsResponse>>(response);
	}

	@Override
	public DataResult<GetRentalResponse> getById(int id) {
		Rental rental = this.rentalRepository.findById(id);
		GetRentalResponse response = this.modelMapperService.forResponse().map(rental, GetRentalResponse.class);
		return new SuccessDataResult<GetRentalResponse>(response, "GET.BY.ID.RENTAL");
	}
	
	@Override
	public Rental getByRentalId(int id) {
		checkIfRental(id);
		return rentalRepository.findById(id);
	}
	
	private void checkIfRental(int id) {
		Rental rental = rentalRepository.findById(id);
		if(rental == null) throw new BusinessException("RENTAL.NOT.FOUND");
	}

	private void checkIfCorporateCustomer(int id) {
		CorporateCustomer corporateCustomer = corporateCustomerService.getByCustomerId(id);
		if (corporateCustomer == null)
			throw new BusinessException("CORPORATE.CUSTOMER.NOT.FOUND");
	}

	private void checkIfIndividualCustomer(int id) {
		IndividualCustomer individualCustomer = individualCustomerService.getByCustomerId(id);
		if (individualCustomer == null)
			throw new BusinessException("INDIVIDUAL.CUSTOMER.NOT.FOUND");
	}

	private void checkIfCar(int id) {
		Car car = carService.getByCarId(id);
		if (car == null)
			throw new BusinessException("CAR.NOT.FOUND!!");
	}

	private void checkIfState(int id) {
		Car car = carService.getByCarId(id);
		if (car.getState() == 2) {
			throw new BusinessException("STATE.DOES.NOT.FIT!!!!");
		}
	}

	private void checkIfDate(Date returnDate, Date pickupDate) {
		if (!pickupDate.after(returnDate)) {
			throw new BusinessException("RETURN.DATE.IS.INCORRECT!!!!");
		}
	}

	private void stateCar(int id) {
		Rental rental = rentalRepository.findById(id);
		Car car = rental.getCar();
		car.setState(1);
	}

	private int dayDifference(Date pickupDate, Date returnDate) {
		int dif = (int) ((returnDate.getTime() - pickupDate.getTime()) / (1000 * 60 * 60 * 24));
		return dif;
	}

	private double fullPrice(long day, double dailyPrice, int pickId, int returnId) {
		if (pickId == returnId) {
			double fullprice = day * dailyPrice;
			return fullprice;
		} else {
			double fullprice = (day * dailyPrice) + 750;
			return fullprice;
		}
	}

	private void ifEqualFindexScore(int carScore, String nationalIdentity) {
		int userScore = findexCheckService.checkFindexScore(nationalIdentity);
		if (userScore < carScore) {
			throw new BusinessException("KULLANICI.FINDEX.SKORU.DÜŞÜK");
		}
	}

}
