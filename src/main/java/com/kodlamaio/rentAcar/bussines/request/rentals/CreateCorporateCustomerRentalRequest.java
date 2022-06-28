package com.kodlamaio.rentAcar.bussines.request.rentals;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateCustomerRentalRequest {
	private Date pickupDate;
	private Date returnDate;
	private int pickupCityId;
	private int returnCityId;
	private int carId;
	private int customerId;
}
