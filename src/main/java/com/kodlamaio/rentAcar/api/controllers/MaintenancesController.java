package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.MaintenanceService;
import com.kodlamaio.rentAcar.bussines.request.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentAcar.bussines.request.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentAcar.bussines.request.maintenances.UpdateMaintenenceRequest;
import com.kodlamaio.rentAcar.bussines.response.maintenances.GetAllMaintenancesResponse;
import com.kodlamaio.rentAcar.bussines.response.maintenances.GetMaintenanceResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenancesController {
	private MaintenanceService maintenanceService;

	public MaintenancesController(MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest) {
		return maintenanceService.add(createMaintenanceRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		return maintenanceService.delete(deleteMaintenanceRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateMaintenenceRequest updateMaintenenceRequest) {
		return maintenanceService.update(updateMaintenenceRequest);
	}
	
	@PostMapping("/updateState")
	public Result updateState(@RequestBody UpdateMaintenenceRequest udateMaintenenceRequest) {
		return maintenanceService.updateState(udateMaintenenceRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllMaintenancesResponse>> getAll(){
		return maintenanceService.getAll();
	}
	
	@GetMapping("getById")
	public DataResult<GetMaintenanceResponse> getById(int id){
		return maintenanceService.getById(id);
	}
	
	
}
