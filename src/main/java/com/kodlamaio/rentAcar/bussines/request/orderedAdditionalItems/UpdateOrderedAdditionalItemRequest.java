package com.kodlamaio.rentAcar.bussines.request.orderedAdditionalItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderedAdditionalItemRequest {
	private int id;
	private int additionalItemId;
	private int rentalId;
}
