package com.kodlamaio.rentAcar.bussines.response.rentals;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCorporateCustomerRentalsResponse {
	private int id;
	private Date pickupDate;
	private Date returnDate;
	private long totalDays;
	private double totalPrice;
	private int pickupCityId;
	private int returnCityId;
	private int carId;
	private int customerId;
}
