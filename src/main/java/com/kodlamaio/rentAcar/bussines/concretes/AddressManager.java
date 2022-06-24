package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.AddressService;
import com.kodlamaio.rentAcar.bussines.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.DeleteAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateBillingAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateContactAddressRequest;
import com.kodlamaio.rentAcar.bussines.response.address.GetAllAddressResponse;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentAcar.entities.concretes.Address;

@Service
public class AddressManager implements AddressService{
	
	private AddressRepository addressRepository;

	private ModelMapperService modelMapperService;
	
	@Autowired
	public AddressManager(AddressRepository addressRepository, ModelMapperService modelMapperService) {
		this.addressRepository = addressRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAddressRequest createAddressRequest) {
		Address address = modelMapperService.forRequest().map(createAddressRequest, Address.class);
		
		if(address.getBillingAddress()==null || address.getBillingAddress() =="") {
			address.setBillingAddress(createAddressRequest.getContactAddress());
		}
		
		addressRepository.save(address);
		return new SuccessResult("ADDRESS.ADDED");
	}

	@Override
	public Result delete(DeleteAddressRequest deleteAddressRequest) {
		// TODO Auto-generated method stub
		return null;
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
	


}
