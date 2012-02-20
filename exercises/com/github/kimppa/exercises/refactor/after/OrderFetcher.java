package com.github.kimppa.exercises.refactor.after;

import java.util.List;

import com.github.kimppa.exercises.refactor.Order;
import com.github.kimppa.exercises.refactor.OrderResponse;
import com.github.kimppa.exercises.refactor.OrdersWebService;
import com.github.kimppa.exercises.refactor.OrdersWebServiceImpl;

public class OrderFetcher {

	private static final int RESPONSE_CODE_OK = 1;
	private OrdersWebService ordersWebService = new OrdersWebServiceImpl();

	public List<Order> getUnbillerOrders(List<Integer> userIds) {
		OrderResponse response = ordersWebService.getUnbilledOrders(userIds);

		validateResponse(response);
		
		return response.getOrders();
	}
	
	private void validateResponse(OrderResponse response) {
		if (response == null) {
			throw new RuntimeException("Response was null");
		}

		// response codes 0=error / 1=ok
		if (response.getResponseCode() != RESPONSE_CODE_OK) {
			throw new RuntimeException("Request failed");
		}
	}
	
}
