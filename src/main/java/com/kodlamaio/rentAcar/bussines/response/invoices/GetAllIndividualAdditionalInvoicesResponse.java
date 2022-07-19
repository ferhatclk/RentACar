package com.kodlamaio.rentAcar.bussines.response.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllIndividualAdditionalInvoicesResponse {
	private int id;
	private String invoiceNumber;
	private String customerFirstName;
	private String customerLastName;
	private String additionalItem;
	private double additionalItemTotalPrice;

}
