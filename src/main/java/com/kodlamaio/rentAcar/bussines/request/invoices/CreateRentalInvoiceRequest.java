package com.kodlamaio.rentAcar.bussines.request.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalInvoiceRequest {
	private String invoiceNumber;
	private int rentalId;
}
