package com.kodlamaio.rentAcar.bussines.response.additionalItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdAdditionalItemResponse {
	private String name;
	private double price;
}
