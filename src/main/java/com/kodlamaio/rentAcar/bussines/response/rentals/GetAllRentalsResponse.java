package com.kodlamaio.rentAcar.bussines.response.rentals;

import java.util.Date;

import com.kodlamaio.rentAcar.entities.concretes.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRentalsResponse {
	private int id;
	private Date pickupDate;
	private Date returnDate;
	private long totalDays;
	private double totalPrice;
	private Car car;
}
