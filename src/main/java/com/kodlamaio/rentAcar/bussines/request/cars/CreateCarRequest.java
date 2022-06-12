package com.kodlamaio.rentAcar.bussines.request.cars;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
	@NotBlank
	@Size(min=2)
	private String description;
	@Min(1000)
	@NotNull
	private double dailyPrice;
	private String plate;
	private int km;
	private int brandId;
	private int colorId;

	
}
