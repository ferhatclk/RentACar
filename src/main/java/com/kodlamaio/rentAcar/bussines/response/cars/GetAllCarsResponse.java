package com.kodlamaio.rentAcar.bussines.response.cars;

import com.kodlamaio.rentAcar.entities.concretes.Brand;
import com.kodlamaio.rentAcar.entities.concretes.Color;

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
	private int state;
	private Brand brand;
	private Color color;
}
