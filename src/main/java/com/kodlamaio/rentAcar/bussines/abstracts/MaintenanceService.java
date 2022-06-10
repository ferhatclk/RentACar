package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentAcar.bussines.request.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentAcar.bussines.request.maintenances.UpdateMaintenenceRequest;
import com.kodlamaio.rentAcar.bussines.response.maintenances.GetMaintenanceResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.entities.concretes.Maintenance;

public interface MaintenanceService {
	Result add(CreateMaintenanceRequest createMaintenanceRequest);
	Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);
	Result update(UpdateMaintenenceRequest updateMaintenenceRequest);
	Result updateState(UpdateMaintenenceRequest updateMaintenenceRequest);
	DataResult<Maintenance> getById(GetMaintenanceResponse getMaintenanceResponse);
	DataResult<List<Maintenance>> getAll();
}
