package com.kodlamaio.rentAcar.bussines.request.individualCustomers;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIndividualCustomerRequest {
	@Size(min = 2)
	private String firstName;
	@Size(min = 2)
	private String lastName;
	@Size(min = 11,max = 11)
	private String nationalIdentity;
	private int birthDay;
	private String email;
	private String password;
	private String customerNumber;
}
