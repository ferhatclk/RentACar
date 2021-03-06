package com.kodlamaio.rentAcar.bussines.request.addresses;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateCustomerAddressRequet {
	@NotNull
	private String contactAddress;

	private String billingAddress;
	private int customerId;
}
