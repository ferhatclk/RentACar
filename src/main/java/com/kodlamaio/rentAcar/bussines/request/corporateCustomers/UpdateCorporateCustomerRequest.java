package com.kodlamaio.rentAcar.bussines.request.corporateCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCorporateCustomerRequest {
	private int corporateCustomerId;
	private String corporateCustomerName;
	private String corporateCustomerTaxNumber;
}
