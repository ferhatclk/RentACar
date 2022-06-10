package com.kodlamaio.rentAcar.bussines.request.rentals;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalRequest {
	private Date pickupDate;
	private Date returnDate;
	private int carId;
}