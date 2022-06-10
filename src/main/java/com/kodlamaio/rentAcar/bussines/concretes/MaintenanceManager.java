package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.MaintenanceService;
import com.kodlamaio.rentAcar.bussines.request.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentAcar.bussines.request.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentAcar.bussines.request.maintenances.UpdateMaintenenceRequest;
import com.kodlamaio.rentAcar.bussines.response.maintenances.GetMaintenanceResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentAcar.dataAccess.abstracts.MaintenanceRepository;
import com.kodlamaio.rentAcar.entities.concretes.Car;
import com.kodlamaio.rentAcar.entities.concretes.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService{
	private MaintenanceRepository maintenanceRepository;
	private CarRepository carRepository;

	public MaintenanceManager(MaintenanceRepository maintenanceRepository, CarRepository carRepository) {
		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		Maintenance maintenance = new Maintenance();
		maintenance.setDateSent(createMaintenanceRequest.getDateSent());
		maintenance.setDateReturned(createMaintenanceRequest.getDateReturned());
		
		Car car = carRepository.findById(createMaintenanceRequest.getCarId());
		car.setId(createMaintenanceRequest.getCarId());
		car.setState(2);
		maintenance.setCar(car);
		
		maintenanceRepository.save(maintenance);
		return new SuccessResult("MAINTENANCE.ADDED");
	}
	
	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		maintenanceRepository.deleteById(deleteMaintenanceRequest.getId());
		return new SuccessResult("MAINTENANCE.DELETED");
	}

	@Override
	public Result update(UpdateMaintenenceRequest updateMaintenenceRequest) {
		
		Maintenance maintenance = maintenanceRepository.findById(updateMaintenenceRequest.getId());
		
		maintenance.setDateSent(updateMaintenenceRequest.getDateSent());
		maintenance.setDateReturned(updateMaintenenceRequest.getDateReturned());
		
		Car car = carRepository.findById(updateMaintenenceRequest.getCarId());
		car.setId(updateMaintenenceRequest.getCarId());
		maintenance.setCar(car);
		
		maintenanceRepository.save(maintenance);
		return new SuccessResult("MAINTENANCE.UPDATE");
	}

	@Override
	public Result updateState(UpdateMaintenenceRequest updateMaintenenceRequest) {
		Car car = carRepository.findById(updateMaintenenceRequest.getCarId());
		if(car.getState()==1) {
			car.setState(2);
		}else {
			car.setState(1);
		}
		carRepository.save(car);
		return new SuccessResult("STATE.UPDATE");
	}

	@Override
	public DataResult<Maintenance> getById(GetMaintenanceResponse getMaintenanceResponse) {
		
		Maintenance maintenance = maintenanceRepository.findById(getMaintenanceResponse.getId());
		
		return new SuccessDataResult<Maintenance>(maintenance,"GET_BY_ID");
	}

	@Override
	public DataResult<List<Maintenance>> getAll() {
		return new SuccessDataResult<List<Maintenance>>(maintenanceRepository.findAll(),"MAINTENACES.LISTED");
	}



}
