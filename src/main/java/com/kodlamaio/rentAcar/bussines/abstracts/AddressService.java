package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.addresses.CreateCorporateCustomerAddressRequet;
import com.kodlamaio.rentAcar.bussines.request.addresses.CreateIndividualCustomerAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.DeleteCorporateCustomerAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.DeleteIndividualCustomerAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateBillingAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateContactAddressRequest;
import com.kodlamaio.rentAcar.bussines.response.address.GetAllAddressResponse;
import com.kodlamaio.rentAcar.bussines.response.address.GetByIdAddressResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface AddressService {
	Result addIndividualCustomerAddress(CreateIndividualCustomerAddressRequest createIndividualCustomerAddressRequest);
	Result addCorporateCustomerAddress(CreateCorporateCustomerAddressRequet createCorparateCustomerAddressRequet);
	Result deleteIndividualCustomerAddress(DeleteIndividualCustomerAddressRequest deleteIndividualCustomerAddressRequest);
	Result deleteCorporateCustomerAddress(DeleteCorporateCustomerAddressRequest deleteCorporateCustomerAddressRequest);
	Result updateContactAddress(UpdateContactAddressRequest updateContactAddressRequest);
	Result updateBillingAddress(UpdateBillingAddressRequest updateBillingAddressRequest);
	DataResult<List<GetAllAddressResponse>> getAll();
	DataResult<GetByIdAddressResponse> getById(int id);
}
