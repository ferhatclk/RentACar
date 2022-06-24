package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.InvoiceService;
import com.kodlamaio.rentAcar.bussines.request.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.request.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.response.invoices.GetAllAdditionalInvoiceResponse;
import com.kodlamaio.rentAcar.bussines.response.invoices.GetAllRentalInvoicesResponse;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentAcar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService{

	private ModelMapperService modelMapperService;

	private InvoiceRepository invoiceRepository;


	public InvoiceManager(ModelMapperService modelMapperService, InvoiceRepository invoiceRepository) {
		this.modelMapperService = modelMapperService;
		this.invoiceRepository = invoiceRepository;
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
        Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setState(1);
        this.invoiceRepository.save(invoice);
        return new SuccessResult("INVOICE.ADDED");
    }

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		Invoice  invoice = invoiceRepository.findById(deleteInvoiceRequest.getId());
		invoice.setState(0);
		this.invoiceRepository.save(invoice);
		//invoiceRepository.delete(invoice);
		return new SuccessResult("INVOICE.CANCEL");
	}

	@Override
	public DataResult<List<GetAllRentalInvoicesResponse>> rentalGetAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
        List<GetAllRentalInvoicesResponse> response =
                invoices.stream().map(invoice -> this.modelMapperService.forResponse()
                        .map(invoice,GetAllRentalInvoicesResponse.class)).collect(Collectors.toList());    
        return new SuccessDataResult<List<GetAllRentalInvoicesResponse>>(response);
	}

	@Override
	public DataResult<List<GetAllAdditionalInvoiceResponse>> additionalGetAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
        List<GetAllAdditionalInvoiceResponse> response =
                invoices.stream().map(invoice -> this.modelMapperService.forResponse()
                        .map(invoice,GetAllAdditionalInvoiceResponse.class)).collect(Collectors.toList());    
        return new SuccessDataResult<List<GetAllAdditionalInvoiceResponse>>(response);
	}
}
