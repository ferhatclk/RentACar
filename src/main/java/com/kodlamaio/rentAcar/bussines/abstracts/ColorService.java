package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.colors.CreateColorRequest;
import com.kodlamaio.rentAcar.bussines.request.colors.DeleteColorRequest;
import com.kodlamaio.rentAcar.bussines.request.colors.UpdateColorRequest;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.entities.concretes.Color;

public interface ColorService {
	Result add(CreateColorRequest createColorRequest);
	
	Result delete(DeleteColorRequest deleteColorRequest);
	
	Result update(UpdateColorRequest updateColorRequest);
	
	DataResult<List<Color>>  getAll();
	
	DataResult<Color>  getById(int id);
}
