package com.kodlamaio.rentAcar.bussines.response.additionals;

import com.kodlamaio.rentAcar.entities.concretes.AdditionalItem;
import com.kodlamaio.rentAcar.entities.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdAdditionalResponse {
	private int id;
	private Rental rental;
	private AdditionalItem additionalItem;
}
