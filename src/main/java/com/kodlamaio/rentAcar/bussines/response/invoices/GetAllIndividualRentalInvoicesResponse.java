package com.kodlamaio.rentAcar.bussines.response.invoices;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllIndividualRentalInvoicesResponse {
	private int id;
	private String invoiceNumber;
	private String customerFirstName;
	private String customerLastName;
	private String brandName;
	private String carPlate;
	private Date rentalReturnDate;
	private Date rentalPickupDate;
	private double rentalTotalPrice;
	private String state;
}

