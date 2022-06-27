package com.kodlamaio.rentAcar.bussines.response.corporateCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCorporateCustomersResponse {
	private int corporateCustomerId;
	private String corporateCustomerName;
	private String corporateCustomerTaxNumber;
}
