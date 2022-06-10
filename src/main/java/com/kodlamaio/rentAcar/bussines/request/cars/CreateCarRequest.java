package com.kodlamaio.rentAcar.bussines.request.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
	
	private String description;
	private double dailyPrice;
	private String plate;
	private int km;
	private int brandId;
	private int colorId;

	
}
