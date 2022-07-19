package com.kodlamaio.rentAcar.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;

import com.kodlamaio.rentAcar.bussines.abstracts.ColorService;
import com.kodlamaio.rentAcar.bussines.request.colors.CreateColorRequest;
import com.kodlamaio.rentAcar.bussines.request.colors.DeleteColorRequest;
import com.kodlamaio.rentAcar.bussines.request.colors.UpdateColorRequest;
import com.kodlamaio.rentAcar.bussines.response.colors.GetAllColorsResponse;
import com.kodlamaio.rentAcar.bussines.response.colors.GetByIdColorResponse;
import com.kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import com.kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentAcar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{
	
	private ColorRepository colorRepository;

	private ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorRepository colorRepository, ModelMapperService modelMapperService) {
		this.colorRepository = colorRepository;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public Result add(CreateColorRequest createColorRequest) {
		checkIfExistColorName(createColorRequest.getName());
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.ADDED");
	}
	
	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		checkIfColor(deleteColorRequest.getId());
		colorRepository.deleteById(deleteColorRequest.getId());
		return new SuccessResult("COLOR.DELETE");
	}
	
	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		colorRepository.save(color);
		return new SuccessResult("COLOR.UPDATE");
	}
	
	@Override
	public DataResult<List<GetAllColorsResponse>>  getAll() {
		List<Color> colors = colorRepository.findAll();
		List<GetAllColorsResponse> response = colors.stream().map(color -> this.modelMapperService.forResponse()
				.map(color, GetAllColorsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllColorsResponse>>(response) ;
	}
	
	@Override
	public DataResult<GetByIdColorResponse>  getById(int id) {
		Color color = colorRepository.findById(id);
		GetByIdColorResponse response = modelMapperService.forResponse().map(color, GetByIdColorResponse.class);
		return new SuccessDataResult<GetByIdColorResponse>(response);
	}
	
	@Override
	public Color getByColorId(int id) {
		checkIfColor(id);
		return colorRepository.findById(id);
	}
	
	private void checkIfColor(int id) {
		Color color = colorRepository.findById(id);
		if(color == null) throw new BusinessException("COLOR.NOT.FOUND");
	}
	
	private void checkIfExistColorName(String name) {
		Color color = colorRepository.findByName(name);
		if(color != null) throw new BusinessException("COLOR.EXİST");
	}


}
