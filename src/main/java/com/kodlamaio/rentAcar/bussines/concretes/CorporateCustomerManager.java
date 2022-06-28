package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.CorporateCustomerService;
import com.kodlamaio.rentAcar.bussines.request.corporateCustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.corporateCustomers.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentAcar.bussines.request.corporateCustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.bussines.response.corporateCustomers.GetAllCorporateCustomersResponse;
import com.kodlamaio.rentAcar.bussines.response.corporateCustomers.GetByIdCorporateCustomerResponse;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentAcar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{
	
	private CorporateCustomerRepository corporateCustomerRepository;
	private ModelMapperService modelMapperService;
	
	public CorporateCustomerManager(CorporateCustomerRepository corporateCustomerRepository, ModelMapperService modelMapperService) {
		this.modelMapperService = modelMapperService;
		this.corporateCustomerRepository = corporateCustomerRepository;
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequet) {
		checkIfCorporateCustomerName(createCorporateCustomerRequet.getCorporateCustomerName());
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequet, CorporateCustomer.class);
		corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.ADDED");
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		checkIfCorporateCustomer(deleteCorporateCustomerRequest.getCorporateCustomerId());
		CorporateCustomer corporateCustomer = corporateCustomerRepository.findById(deleteCorporateCustomerRequest.getCorporateCustomerId());
		corporateCustomerRepository.delete(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.DELETED");
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		checkIfCorporateCustomer(updateCorporateCustomerRequest.getCorporateCustomerId());
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
		corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.UPDATED");
	}

	@Override
	public DataResult<List<GetAllCorporateCustomersResponse>> getAll() {
		List<CorporateCustomer> corporateCustomers = corporateCustomerRepository.findAll();
		List<GetAllCorporateCustomersResponse> response = corporateCustomers.stream().map(corporateCustomer -> modelMapperService.forResponse()
				.map(corporateCustomer, GetAllCorporateCustomersResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCorporateCustomersResponse>>(response);
	}

	@Override
	public DataResult<GetByIdCorporateCustomerResponse> getById(int id) {
		checkIfCorporateCustomer(id);
		CorporateCustomer corporateCustomer = corporateCustomerRepository.findById(id);
		GetByIdCorporateCustomerResponse response = modelMapperService.forResponse().map(corporateCustomer, GetByIdCorporateCustomerResponse.class);
		return new SuccessDataResult<GetByIdCorporateCustomerResponse>(response);
	}
	
	@Override
	public CorporateCustomer getByCustomerId(int id) {
		checkIfCorporateCustomer(id);
		return corporateCustomerRepository.findById(id);
	}
	
	
	private void checkIfCorporateCustomerName(String name) {
		CorporateCustomer corporateCustomer = corporateCustomerRepository.findByCorporateCustomerName(name);
		if(corporateCustomer !=null) throw new BusinessException("CORPORATE.CUSTOMER.EXİST");
	}
	
	private void checkIfCorporateCustomer(int id) {
		CorporateCustomer corporateCustomer = corporateCustomerRepository.findById(id);
		if(corporateCustomer == null) throw new BusinessException("CORPORATE.CUSTOMER.NOT.FOUND");
	}

}
