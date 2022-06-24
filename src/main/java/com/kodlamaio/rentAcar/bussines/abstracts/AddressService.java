package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.addresses.CreateAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.DeleteAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateBillingAddressRequest;
import com.kodlamaio.rentAcar.bussines.request.addresses.UpdateContactAddressRequest;
import com.kodlamaio.rentAcar.bussines.response.address.GetAllAddressResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface AddressService {
	Result add(CreateAddressRequest createAddressRequest);
	Result delete(DeleteAddressRequest deleteAddressRequest);
	Result updateContactAddress(UpdateContactAddressRequest updateContactAddressRequest);
	Result updateBillingAddress(UpdateBillingAddressRequest updateBillingAddressRequest);
	DataResult<List<GetAllAddressResponse>> getAll();
}
