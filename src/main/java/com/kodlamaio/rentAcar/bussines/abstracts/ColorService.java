package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.colors.CreateColorRequest;
import com.kodlamaio.rentAcar.bussines.request.colors.DeleteColorRequest;
import com.kodlamaio.rentAcar.bussines.request.colors.UpdateColorRequest;
import com.kodlamaio.rentAcar.bussines.response.colors.GetAllColorsResponse;
import com.kodlamaio.rentAcar.bussines.response.colors.GetByIdColorResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;

public interface ColorService {
	Result add(CreateColorRequest createColorRequest);
	
	Result delete(DeleteColorRequest deleteColorRequest);
	
	Result update(UpdateColorRequest updateColorRequest);
	
	DataResult<List<GetAllColorsResponse>>  getAll();
	
	DataResult<GetByIdColorResponse>  getById(int id);
}
