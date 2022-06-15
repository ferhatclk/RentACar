package com.kodlamaio.rentAcar.bussines.request.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarRequest {
	private int id;
	private String description;
	private double dailyPrice;
	private String plate;
	private int findexScore;
	private int km;
	private int brandId;
	private int colorId;
}
