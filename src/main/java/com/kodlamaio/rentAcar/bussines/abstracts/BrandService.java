package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.brands.CreateBrandRequest;
import com.kodlamaio.rentAcar.bussines.request.brands.DeleteBrandRequest;
import com.kodlamaio.rentAcar.bussines.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentAcar.bussines.response.brands.GetAllBrandsResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
//import com.kodlamaio.rentAcar.bussines.request.brands.GetBrandRequest;
import com.kodlamaio.rentAcar.entities.concretes.Brand;

public interface BrandService {
	Result add(CreateBrandRequest createBrandRequest);
	
	Result delete(DeleteBrandRequest deleteBrandRequest);
	
	Result update(UpdateBrandRequest updateBrandRequest);
	
	DataResult<List<GetAllBrandsResponse>>  getAll();
	
	DataResult<Brand> getById(int id);
	
}
