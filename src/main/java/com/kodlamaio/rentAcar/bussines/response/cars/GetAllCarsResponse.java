package com.kodlamaio.rentAcar.bussines.response.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarsResponse {
	private int id;
	private String description;
	private double dailyPrice;
	private String plate;
	private int km;
	private int findexScore;
	private int state;
	private int brandId;
	private int colorId;
}
