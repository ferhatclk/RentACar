package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.MaintenanceService;
import com.kodlamaio.rentAcar.bussines.request.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentAcar.bussines.request.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentAcar.bussines.request.maintenances.UpdateMaintenenceRequest;
import com.kodlamaio.rentAcar.bussines.response.maintenances.GetAllMaintenancesResponse;
import com.kodlamaio.rentAcar.bussines.response.maintenances.GetMaintenanceResponse;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
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

	private ModelMapperService modelMapperService;

	private CarRepository carRepository;

	public MaintenanceManager(MaintenanceRepository maintenanceRepository, CarRepository carRepository, ModelMapperService modelMapperService) {
		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		
		ifCheckState(createMaintenanceRequest.getCarId());
		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
		
		Car car = carRepository.findById(createMaintenanceRequest.getCarId());

		car.setState(2);
		maintenance.setCar(car);
		
		maintenanceRepository.save(maintenance);
		return new SuccessResult("MAINTENANCE.ADDED");
	}
	
	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		Maintenance maintenance = maintenanceRepository.findById(deleteMaintenanceRequest.getId());
		Car car = maintenance.getCar();
		car.setState(1);
		carRepository.save(car);
		maintenanceRepository.deleteById(deleteMaintenanceRequest.getId());
		return new SuccessResult("MAINTENANCE.DELETED");
	}

	@Override
	public Result update(UpdateMaintenenceRequest updateMaintenenceRequest) {
		ifCheckState(updateMaintenenceRequest.getCarId());
		stateCar(updateMaintenenceRequest.getId());
		Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenenceRequest, Maintenance.class);
		
		Car car = this.carRepository.findById(updateMaintenenceRequest.getCarId());
		car.setState(2);
//		carRepository.save(car);
		maintenance.setCar(car);

		maintenanceRepository.save(maintenance);
		
		return new SuccessResult("MAINTENANCE.UPDATE");
	}

	@Override
	public DataResult<GetMaintenanceResponse> getById(int id) {
		
		Maintenance maintenance = maintenanceRepository.findById(id);
		GetMaintenanceResponse response = this.modelMapperService.forResponse().map(maintenance, GetMaintenanceResponse.class);
		return new SuccessDataResult<GetMaintenanceResponse>(response,"GET_BY_ID");
	}

	@Override
	public DataResult<List<GetAllMaintenancesResponse>> getAll() {
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();
		List<GetAllMaintenancesResponse> response = maintenances.stream().map(maintenance -> this.modelMapperService.forResponse()
				.map(maintenance, GetAllMaintenancesResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllMaintenancesResponse>>(response,"MAINTENACES.LISTED");
	}
	
	private void ifCheckState(int id) {
		Car car = carRepository.findById(id);
		if(car.getState()==3) {
			throw new BusinessException("STATE.DOES.NOT.FIT!!!!");
		}
	}
	
	private void stateCar(int id) {
		Maintenance maintenance = maintenanceRepository.findById(id);
		Car car = maintenance.getCar();
		car.setState(1);
	}


}
