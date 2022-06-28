package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.AddressService;
import com.kodlamaio.rentAcar.bussines.abstracts.CorporateCustomerService;
import com.kodlamaio.rentAcar.bussines.abstracts.IndividualCustomerService;
import com.kodlamaio.rentAcar.bussines.request.addresses.CreateCorporateCustomerAddressRequet;
import com.kodlamaio.rentAcar.bussines.request.addresses.CreateIndividualCustomerAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.DeleteCorporateCustomerAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.DeleteIndividualCustomerAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateBillingAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateContactAddressRequest;
import com.kodlamaio.rentAcar.bussines.response.address.GetAllAddressResponse;
import com.kodlamaio.rentAcar.bussines.response.address.GetByIdAddressResponse;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentAcar.entities.concretes.Address;
import com.kodlamaio.rentAcar.entities.concretes.CorporateCustomer;
import com.kodlamaio.rentAcar.entities.concretes.IndividualCustomer;

@Service
public class AddressManager implements AddressService{
	
	private AddressRepository addressRepository;

	private ModelMapperService modelMapperService;
	
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public AddressManager(AddressRepository addressRepository, ModelMapperService modelMapperService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService) {
		
		this.addressRepository = addressRepository;
		this.modelMapperService = modelMapperService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
	}

	@Override
	public Result addIndividualCustomerAddress(CreateIndividualCustomerAddressRequest createIndividualCustomerAddressRequest) {
		checkIfIndividualCustomer(createIndividualCustomerAddressRequest.getCustomerId());
		Address address = modelMapperService.forRequest().map(createIndividualCustomerAddressRequest, Address.class);
		
		if(address.getBillingAddress()==null || address.getBillingAddress() =="") {
			address.setBillingAddress(createIndividualCustomerAddressRequest.getContactAddress());
		}
		
		addressRepository.save(address);
		return new SuccessResult("ADDRESS.ADDED");
	}
	
	@Override
	public Result addCorporateCustomerAddress(CreateCorporateCustomerAddressRequet createCorparateCustomerAddressRequet) {
		checkIfCorporateCustomer(createCorparateCustomerAddressRequet.getCustomerId());
		Address address = modelMapperService.forRequest().map(createCorparateCustomerAddressRequet, Address.class);
		
		if(address.getBillingAddress()==null || address.getBillingAddress() =="") {
			address.setBillingAddress(createCorparateCustomerAddressRequet.getContactAddress());
		}
		
		addressRepository.save(address);
		return new SuccessResult("ADDRESS.ADDED");
	}
	
	@Override
	public Result deleteCorporateCustomerAddress(DeleteCorporateCustomerAddressRequest deleteCorporateCustomerAddressRequest) {
		addressRepository.deleteById(deleteCorporateCustomerAddressRequest.getId());
		return new SuccessResult("ADDRESS.DELETED");
	}


	@Override
	public Result deleteIndividualCustomerAddress(DeleteIndividualCustomerAddressRequest deleteIndividualCustomerAddressRequest) {
		addressRepository.deleteById(deleteIndividualCustomerAddressRequest.getId());
		return new SuccessResult("ADDRESS.DELETED");
	}

	@Override
	public Result updateContactAddress(UpdateContactAddressRequest updateContactAddressRequest) {
		Address address = addressRepository.findById(updateContactAddressRequest.getId());
		address.setContactAddress(updateContactAddressRequest.getContactAddress());
		addressRepository.save(address);
		return new SuccessResult("CONTACT.ADDRESS.UPDATED");
	}

	@Override
	public Result updateBillingAddress(UpdateBillingAddressRequest updateBillingAddressRequest) {
		Address address = addressRepository.findById(updateBillingAddressRequest.getId());
		address.setContactAddress(updateBillingAddressRequest.getBillingAddress());
		addressRepository.save(address);
		return new SuccessResult("BILLING.ADDRESS.UPDATED");
	}

	@Override
	public DataResult<List<GetAllAddressResponse>> getAll() {
		List<Address> addresses = addressRepository.findAll();
		List<GetAllAddressResponse> response = addresses.stream().map(address -> modelMapperService.forResponse()
				.map(address, GetAllAddressResponse.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllAddressResponse>>(response);
	}

	@Override
	public DataResult<GetByIdAddressResponse> getById(int id) {
		Address address = addressRepository.findById(id);
		GetByIdAddressResponse response = modelMapperService.forResponse().map(address, GetByIdAddressResponse.class);
		return new SuccessDataResult<GetByIdAddressResponse>(response);
	}
	
	private void checkIfIndividualCustomer(int id) {
		IndividualCustomer individualCustomer = individualCustomerService.getByCustomerId(id);
		if(individualCustomer == null) {
			throw new BusinessException("INDIVIDUAL.CUSTOMER.NOT.FOUND!!!");
		}
	}
	
	private void checkIfCorporateCustomer(int id) {
		CorporateCustomer corporateCustomer = corporateCustomerService.getByCustomerId(id);
		if(corporateCustomer == null) {
			throw new BusinessException("CORPORATE.CUSTOMER.NOT.FOUND!!!");
		}
	}

}