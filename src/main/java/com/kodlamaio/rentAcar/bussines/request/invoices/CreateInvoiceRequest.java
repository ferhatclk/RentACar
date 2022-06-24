package com.kodlamaio.rentAcar.bussines.request.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
	private String invoiceNumber;
	private int rentalId;
}
