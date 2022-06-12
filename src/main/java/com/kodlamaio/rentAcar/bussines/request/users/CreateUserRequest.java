package com.kodlamaio.rentAcar.bussines.request.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
	@NotBlank
	@Size(min = 2)
	private String firstName;
	@NotNull
	@Size(min = 2)
	private String lastName;
	@NotNull
	@Size(min = 11,max = 11)
	private String tcNo;
	private String email;
	private String password;
}
