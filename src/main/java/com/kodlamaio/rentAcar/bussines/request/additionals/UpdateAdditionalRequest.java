package com.kodlamaio.rentAcar.bussines.request.additionals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdditionalRequest {
	private int id;
	private int additionalItemId;
	private int rentalId;
}