package com.kodlamaio.rentAcar.bussines.response.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAdditionalInvoiceResponse {
	private int id;
	private String invoiceNumber;
	private String userFirstName;
	private String userLastName;
	private String additionalItem;

}
