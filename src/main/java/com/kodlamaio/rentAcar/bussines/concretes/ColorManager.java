package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;

import com.kodlamaio.rentAcar.bussines.abstracts.ColorService;
import com.kodlamaio.rentAcar.bussines.request.colors.CreateColorRequest;
import com.kodlamaio.rentAcar.bussines.request.colors.DeleteColorRequest;
import com.kodlamaio.rentAcar.bussines.request.colors.UpdateColorRequest;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentAcar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{
	private ColorRepository colorRepository;
	
	@Autowired
	public ColorManager(ColorRepository colorRepository) {
		this.colorRepository = colorRepository;
	}
	
	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Color color = new Color();
		color.setName(createColorRequest.getName());
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.ADDED");
	}
	
	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		colorRepository.deleteById(deleteColorRequest.getId());
		return new SuccessResult("COLOR.DELETE");
	}
	
	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = colorRepository.findById(updateColorRequest.getId());
		color.setName(updateColorRequest.getName());
		colorRepository.save(color);
		return new SuccessResult("COLOR.UPDATE");
	}
	
	@Override
	public DataResult<List<Color>>  getAll() {

		return new SuccessDataResult<List<Color>>(colorRepository.findAll(),"COLOR.LİSTED") ;
	}
	
	@Override
	public DataResult<Color>  getById(int id) {
		
		return new SuccessDataResult<Color>(colorRepository.findById(id),"COLOR_ID.GET");
	}

}
