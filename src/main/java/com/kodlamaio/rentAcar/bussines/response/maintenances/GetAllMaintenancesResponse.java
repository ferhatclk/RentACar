package com.kodlamaio.rentAcar.bussines.response.maintenances;

import java.util.Date;

import com.kodlamaio.rentAcar.entities.concretes.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMaintenancesResponse {
	private int id;
	private Date dateSent;
	private Date dateReturned;
	private Car car;
}
