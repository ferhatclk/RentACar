package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.BrandService;
import com.kodlamaio.rentAcar.bussines.request.brands.CreateBrandRequest;
import com.kodlamaio.rentAcar.bussines.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentAcar.bussines.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentAcar.bussines.response.brands.GetAllBrandsResponse;
import com.kodlamaio.rentAcar.bussines.response.brands.GetByIdBrandResponse;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentAcar.entities.concretes.Brand;
//brandServiceImpl
@Service
public class BrandManager implements BrandService{
	
	private BrandRepository brandRepository;

	private ModelMapperService modelMapperService;
	
	@Autowired // parametreye bakıp bu interface nin somutunu getir
	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {
		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		
		return new SuccessResult("BRAND.ADDED");
	}
	
	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		checkIfBrand(deleteBrandRequest.getId());
		brandRepository.deleteById(deleteBrandRequest.getId());
		return new SuccessResult("BRAND.DELETE");
	}
	
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Brand brandToUpdate = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		brandRepository.save(brandToUpdate);
		return new SuccessResult("BRAND.UPDATE");
	}

	@Override
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		List<Brand> brands = this.brandRepository.findAll();
		List<GetAllBrandsResponse> response = brands.stream().map(brand -> this.modelMapperService.forResponse()
				.map(brand,GetAllBrandsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBrandsResponse>>(response);
	}


	@Override
	public DataResult<GetByIdBrandResponse> getById(int id) {
		Brand brand = this.brandRepository.findById(id);
		GetByIdBrandResponse response = modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
		return new SuccessDataResult<GetByIdBrandResponse>(response);
	}

	private void checkIfBrand(int id) {
		Brand brand = brandRepository.findById(id);
		if(brand == null) throw new BusinessException("BRAND.NOT.FOUND!!");
	}
	
	private void checkIfBrandExistByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if(currentBrand!=null) throw new BusinessException("BRAND.EXİST");
	}
	
}
