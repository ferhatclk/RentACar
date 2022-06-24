package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.request.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.response.invoices.GetAllAdditionalInvoiceResponse;
import com.kodlamaio.rentAcar.bussines.response.invoices.GetAllRentalInvoicesResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface InvoiceService {
	Result add(CreateInvoiceRequest createInvoiceRequest);
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
	DataResult<List<GetAllRentalInvoicesResponse>> rentalGetAll();
	DataResult<List<GetAllAdditionalInvoiceResponse>> additionalGetAll();
}
