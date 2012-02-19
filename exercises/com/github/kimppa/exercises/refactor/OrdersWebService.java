package com.github.kimppa.exercises.refactor;

import java.util.List;

public interface OrdersWebService {
	
	public OrderResponse getUnbilledOrders(List<Integer> userIds);

}
