package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.InvoiceService;
import com.kodlamaio.rentAcar.bussines.abstracts.OrderedAdditionalItemService;
import com.kodlamaio.rentAcar.bussines.abstracts.RentalService;
import com.kodlamaio.rentAcar.bussines.request.invoices.CreateAdditionalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.request.invoices.CreateRentalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.request.invoices.DeleteAdditionalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.request.invoices.DeleteRentalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.response.invoices.GetAllAdditionalInvoiceResponse;
import com.kodlamaio.rentAcar.bussines.response.invoices.GetAllRentalInvoicesResponse;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentAcar.entities.concretes.Invoice;
import com.kodlamaio.rentAcar.entities.concretes.OrderedAdditionalItem;
import com.kodlamaio.rentAcar.entities.concretes.Rental;

@Service
public class InvoiceManager implements InvoiceService{

	private ModelMapperService modelMapperService;

	private InvoiceRepository invoiceRepository;
	
	private RentalService rentalService;
	private OrderedAdditionalItemService orderedAdditionalItemService;


	public InvoiceManager(ModelMapperService modelMapperService, InvoiceRepository invoiceRepository,
			RentalService rentalService,OrderedAdditionalItemService orderedAdditionalItemService) {
		
		this.modelMapperService = modelMapperService;
		this.invoiceRepository = invoiceRepository;
		this.rentalService = rentalService;
		this.orderedAdditionalItemService = orderedAdditionalItemService;
	}

	@Override
	public Result addForIndividualCustomerRentalInvoice(CreateRentalInvoiceRequest createRentalInvoiceRequest) {
		checkIfRental(createRentalInvoiceRequest.getRentalId());
        Invoice invoice = this.modelMapperService.forRequest().map(createRentalInvoiceRequest, Invoice.class);
		invoice.setState(1);
        this.invoiceRepository.save(invoice);
        return new SuccessResult("INVOICE.ADDED.FOR.INDIVIDUAL.RENTAL");
    }
	
	@Override
	public Result addForIndividualCustomerAdditionalInvoice(CreateAdditionalInvoiceRequest createAdditionalInvoiceRequest) {
		checkIfOrderedAdditionalItem(createAdditionalInvoiceRequest.getOrderedAdditionalItemId());
		Invoice invoice = this.modelMapperService.forRequest().map(createAdditionalInvoiceRequest, Invoice.class);
        invoice.setState(1);
        this.invoiceRepository.save(invoice);
        return new SuccessResult("INVOICE.ADDED.FOR.INDIVIDUAL.ADDITIONAL");
	}

	@Override
	public Result addForCorporateCustomerRentalInvoice(CreateRentalInvoiceRequest createRentalInvoiceRequest) {
		checkIfRental(createRentalInvoiceRequest.getRentalId());
        Invoice invoice = this.modelMapperService.forRequest().map(createRentalInvoiceRequest, Invoice.class);
        invoice.setState(1);
        this.invoiceRepository.save(invoice);
        return new SuccessResult("INVOICE.ADDED.FOR.CORPORATE.RENTAL");
	}

	@Override
	public Result addForCorporateCustomerAdditionalInvoice(CreateAdditionalInvoiceRequest createAdditionalInvoiceRequest) {
		checkIfOrderedAdditionalItem(createAdditionalInvoiceRequest.getOrderedAdditionalItemId());
		Invoice invoice = this.modelMapperService.forRequest().map(createAdditionalInvoiceRequest, Invoice.class);
        invoice.setState(1);
        this.invoiceRepository.save(invoice);
        return new SuccessResult("INVOICE.ADDED.FOR.CORPORATE.ADDITIONAL");
	}
	
	@Override
	public Result deleteForIndividualCustomerRentalInvoice(DeleteRentalInvoiceRequest deleteRentalInvoiceRequest) {
		checkIfInvoice(deleteRentalInvoiceRequest.getId());
		Invoice  invoice = invoiceRepository.findById(deleteRentalInvoiceRequest.getId());
		invoice.setState(0);
		this.invoiceRepository.save(invoice);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.RENTAL.INVOICE.CANCEL");
	}

	@Override
	public Result deleteForIndividualCustomerAdditionalInvoice(
			DeleteAdditionalInvoiceRequest deleteAdditionalInvoiceRequest) {
		checkIfInvoice(deleteAdditionalInvoiceRequest.getId());
		Invoice  invoice = invoiceRepository.findById(deleteAdditionalInvoiceRequest.getId());
		invoice.setState(0);
		this.invoiceRepository.save(invoice);
		return new SuccessResult("INDIVIDUAL.CUSTOMER.ADDITIONAL.INVOICE.CANCEL");
	}

	@Override
	public Result deleteForCorporateCustomerRentalInvoice(DeleteRentalInvoiceRequest deleteRentalInvoiceRequest) {
		checkIfInvoice(deleteRentalInvoiceRequest.getId());
		Invoice  invoice = invoiceRepository.findById(deleteRentalInvoiceRequest.getId());
		invoice.setState(0);
		this.invoiceRepository.save(invoice);
		return new SuccessResult("CORPORATE.CUSTOMER.RENTAL.INVOICE.CANCEL");
	}

	@Override
	public Result deleteForCorporateCustomerAdditionalInvoice(
			DeleteAdditionalInvoiceRequest deleteAdditionalInvoiceRequest) {
		checkIfInvoice(deleteAdditionalInvoiceRequest.getId());
		Invoice  invoice = invoiceRepository.findById(deleteAdditionalInvoiceRequest.getId());
		invoice.setState(0);
		this.invoiceRepository.save(invoice);
		return new SuccessResult("CORPORATE.CUSTOMER.ADDITIONAL.INVOICE.CANCEL");
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
	
	private void checkIfRental(int id) {
		Rental rental = rentalService.getByRentalId(id);
		if(rental == null) throw new BusinessException("RENTAL.NOT.FOUND");
	}
	
	private void checkIfOrderedAdditionalItem(int id) {
		OrderedAdditionalItem orderedAdditionalItem = orderedAdditionalItemService.getByOrderedAdditionalItemId(id);
		if(orderedAdditionalItem == null) throw new BusinessException("ORDERED.ADDITIONAL.ITEM.NOT.FOUND");
	}
	
	private void checkIfInvoice(int id) {
		Invoice invoice = invoiceRepository.findById(id);
		if(invoice == null) throw new BusinessException("INVOICE.NOT.FOUND");
	}
	

}
