package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.bussines.abstracts.InvoiceService;
import com.kodlamaio.rentAcar.bussines.request.invoices.CreateAdditionalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.request.invoices.CreateRentalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.request.invoices.DeleteAdditionalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.request.invoices.DeleteRentalInvoiceRequest;
import com.kodlamaio.rentAcar.bussines.response.invoices.GetAllAdditionalInvoiceResponse;
import com.kodlamaio.rentAcar.bussines.response.invoices.GetAllRentalInvoicesResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/invoices/")
public class InvoicesController {
	
	private InvoiceService invoiceService;
	
	@Autowired
	public InvoicesController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@PostMapping("addForIndividualCustomerRental")
	public Result addForIndividualCustomerRental(@RequestBody CreateRentalInvoiceRequest createRentalInvoiceRequest) {
		return this.invoiceService.addForIndividualCustomerRentalInvoice(createRentalInvoiceRequest);
	}
	
	@PostMapping("addForIndividualCustomerAdditional")
	public Result addForIndividualCustomerAdditional(@RequestBody CreateAdditionalInvoiceRequest createAdditionalInvoiceRequest) {
		return this.invoiceService.addForIndividualCustomerAdditionalInvoice(createAdditionalInvoiceRequest);
	}
	
	@PostMapping("addForCorporateCustomerRental")
	public Result addForCorporateCustomerRental(@RequestBody CreateRentalInvoiceRequest createRentalInvoiceRequest) {
		return this.invoiceService.addForCorporateCustomerRentalInvoice(createRentalInvoiceRequest);
	}
	
	@PostMapping("addForCorporateCustomerAdditional")
	public Result addForCorporateCustomerAdditional(@RequestBody CreateAdditionalInvoiceRequest createAdditionalInvoiceRequest) {
		return this.invoiceService.addForCorporateCustomerAdditionalInvoice(createAdditionalInvoiceRequest);
	}
	
	@PostMapping("deleteForIndividualRentalInvoice")
	public Result deleteForIndividualCustomerRentalInvoice(DeleteRentalInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.deleteForIndividualCustomerRentalInvoice(deleteInvoiceRequest);
	}
	
	@PostMapping("deleteForIndividualAdditionalInvoice")
	public Result deleteForIndividualCustomerAdditionalInvoice(DeleteAdditionalInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.deleteForIndividualCustomerAdditionalInvoice(deleteInvoiceRequest);
	}
	
	@PostMapping("deleteForCorporateRentalInvoice")
	public Result deleteForCorporateCustomerRentalInvoice(DeleteRentalInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.deleteForCorporateCustomerRentalInvoice(deleteInvoiceRequest);
	}
	
	@PostMapping("deleteForCorporateAdditionalInvoice")
	public Result deleteForCorporateCustomerAdditionalInvoice(DeleteAdditionalInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.deleteForCorporateCustomerAdditionalInvoice(deleteInvoiceRequest);
	}
	
	@GetMapping("getalladditional")
	public DataResult<List<GetAllAdditionalInvoiceResponse>> additionalGetAll(){
		return this.invoiceService.additionalGetAll();
	}
	
	@GetMapping("getallrental")
	public DataResult<List<GetAllRentalInvoicesResponse>> rentalGetAll(){
		return this.invoiceService.rentalGetAll();
	}
}
