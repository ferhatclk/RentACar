package com.kodlamaio.rentAcar.bussines.response.rentals;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRentalResponse {
	private int id;
	private Date pickupDate;
	private Date returnDate;
	private long totalDays;
	private double totalPrice;
	private int pickUpCityId;
	private int returnCityId;
	private int carId;
}
