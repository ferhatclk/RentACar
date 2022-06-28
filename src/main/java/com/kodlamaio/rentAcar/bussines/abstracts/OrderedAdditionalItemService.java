package com.kodlamaio.rentAcar.bussines.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.bussines.request.orderedAdditionalItems.CreateOrderedAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.orderedAdditionalItems.DeleteOrderedAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.request.orderedAdditionalItems.UpdateOrderedAdditionalItemRequest;
import com.kodlamaio.rentAcar.bussines.response.orderedAdditionalItem.GetAllOrderedAdditionalItemsResponse;
import com.kodlamaio.rentAcar.bussines.response.orderedAdditionalItem.GetByIdOrderedAdditionalItemResponse;
import com.kodlamaio.rentAcar.core.utilities.result.DataResult;
import com.kodlamaio.rentAcar.core.utilities.result.Result;
import com.kodlamaio.rentAcar.entities.concretes.OrderedAdditionalItem;

public interface OrderedAdditionalItemService {
	Result add(CreateOrderedAdditionalItemRequest createAdditionalRequest);
	Result delete(DeleteOrderedAdditionalItemRequest deleteAdditionalRequest);
	Result update(UpdateOrderedAdditionalItemRequest updateAdditionalRequest);
	DataResult<List<GetAllOrderedAdditionalItemsResponse>> getAll();
	DataResult<GetByIdOrderedAdditionalItemResponse> getById(int id);
	OrderedAdditionalItem getByOrderedAdditionalItemId(int id);
}
