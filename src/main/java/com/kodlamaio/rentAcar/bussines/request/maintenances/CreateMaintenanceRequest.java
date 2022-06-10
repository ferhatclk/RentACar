package com.kodlamaio.rentAcar.bussines.request.maintenances;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceRequest {
	private Date dateSent;
	private Date dateReturned;
	private int carId;

}
