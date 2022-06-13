package com.kodlamaio.rentAcar.bussines.request.additionalItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdditionalItemRequest {
	private String name;
	private double price;
}
