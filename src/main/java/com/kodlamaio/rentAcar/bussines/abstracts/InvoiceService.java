package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.invoices.CreateAdditionalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.request.invoices.CreateRentalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.request.invoices.DeleteAdditionalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.request.invoices.DeleteRentalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.response.invoices.GetAllAdditionalInvoiceResponse;
import com.kodlamaio.rentAcar.bussines.response.invoices.GetAllRentalInvoicesResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface InvoiceService {
	Result addForIndividualCustomerRentalInvoice(CreateRentalInvoiceRequest createRentalInvoiceRequest);
	Result addForIndividualCustomerAdditionalInvoice(CreateAdditionalInvoiceRequest createAdditionalInvoiceRequest);
	
	Result addForCorporateCustomerRentalInvoice(CreateRentalInvoiceRequest createRentalInvoiceRequest);
	Result addForCorporateCustomerAdditionalInvoice(CreateAdditionalInvoiceRequest createACreateRentalInvoiceRequest);
	
	Result deleteForIndividualCustomerRentalInvoice(DeleteRentalInvoiceRequest deleteRentalInvoiceRequest);
	Result deleteForIndividualCustomerAdditionalInvoice(DeleteAdditionalInvoiceRequest deleteAdditionalInvoiceRequest);
	
	Result deleteForCorporateCustomerRentalInvoice(DeleteRentalInvoiceRequest deleteRentalInvoiceRequest);
	Result deleteForCorporateCustomerAdditionalInvoice(DeleteAdditionalInvoiceRequest deleteAdditionalInvoiceRequest);
	
	DataResult<List<GetAllRentalInvoicesResponse>> rentalGetAll();
	DataResult<List<GetAllAdditionalInvoiceResponse>> additionalGetAll();
	
	
}
