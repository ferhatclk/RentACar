package com.kodlamaio.rentAcar.bussines.request.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBillingAddressRequest {
	private int id;
	private String billingAddress;
}
