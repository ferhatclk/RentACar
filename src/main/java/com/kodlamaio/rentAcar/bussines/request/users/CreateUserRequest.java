package com.kodlamaio.rentAcar.bussines.request.users;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

	@Size(min = 2)
	private String firstName;
	@Size(min = 2)
	private String lastName;
	@Size(min = 11,max = 11)
	private String tcNo;
	private String email;
	private String password;
}
