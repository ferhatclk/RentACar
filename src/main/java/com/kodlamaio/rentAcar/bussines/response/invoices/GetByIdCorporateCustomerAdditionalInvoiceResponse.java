package com.kodlamaio.rentAcar.bussines.response.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdCorporateCustomerAdditionalInvoiceResponse {
	private int id;
	private String invoiceNumber;
	private String customerCorporateCustomerName;
	private String additionalItem;
	private double additionalItemTotalPrice;
}
