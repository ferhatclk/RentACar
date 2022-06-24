package com.kodlamaio.rentAcar.bussines.response.invoices;

import java.util.List;

import com.kodlamaio.rentAcar.entities.concretes.OrderedAdditionalItem;

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
