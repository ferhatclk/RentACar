package com.kodlamaio.rentAcar.bussines.response.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdAddressResponse {
	private int id;
	private String contactAddress;
	private String billingAddress;
	private int customerId;
}
