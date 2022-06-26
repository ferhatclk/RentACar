package com.kodlamaio.rentAcar.bussines.response.orderedAdditionalItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdOrderedAdditionalItemResponse {
	private int id;
	private int rentalId;
	private int additionalItemId;
	private double totalPrice;
	private long days;
}
