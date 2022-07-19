package com.kodlamaio.rentAcar.bussines.response.invoices;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCorporateRentalInvoicesResponse {
	private int id;
	private String invoiceNumber;
	private String customerCorporateCustomerName;
	private String brandName;
	private String carPlate;
	private Date rentalReturnDate;
	private Date rentalPickupDate;
	private double rentalTotalPrice;
	private String state;
}
