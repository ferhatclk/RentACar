package com.kodlamaio.rentAcar.bussines.response.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdUserResponse {
	private int id;
	private String firstName;
	private String lastName;
	private String nationalIdentity;
	private int birthDay;
	private String email;
	private String password;
//	private String address;
}
