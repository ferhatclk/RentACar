package com.kodlamaio.rentAcar.bussines.response.individualCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllIndividualCustomersResponse {
	private int individualCustomerId;
	private String firstName;
	private String lastName;
	private String nationalIdentity;
	private int birthDay;
	private String email;
	private String password;
	private String customerNumber;
}
