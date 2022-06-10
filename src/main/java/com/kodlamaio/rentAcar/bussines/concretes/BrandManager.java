package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.bussines.abstracts.BrandService;
import com.kodlamaio.rentAcar.bussines.request.brands.CreateBrandRequest;
import com.kodlamaio.rentAcar.bussines.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentAcar.bussines.request.brands.UpdateBrandRequest;
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
	
	@Autowired // parametreye bakıp bu interface nin somutunu getir
	public BrandManager(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}


	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		Brand brand = new Brand();
		brand.setName(createBrandRequest.getName());
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.ADDED");
	}
	
	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		brandRepository.deleteById(deleteBrandRequest.getId());
		return new SuccessResult("BRAND.DELETE");
	}
	
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		
		Brand brandToUpdate = brandRepository.findById(updateBrandRequest.getId());
		brandToUpdate.setName(updateBrandRequest.getName());
		brandRepository.save(brandToUpdate);
		return new SuccessResult("BRAND.UPDATE");
	}


	@Override
	public DataResult<List<Brand>> getAll() {
		
		return new SuccessDataResult<List<Brand>>(brandRepository.findAll(),"BRAND.LISTED");
	}


	@Override
	public DataResult<Brand> getById(int id) {
		return new SuccessDataResult<Brand>(this.brandRepository.findById(id));
	}


	
}
