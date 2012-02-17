package com.github.kimppa.exercises.refactor;

<<<<<<< HEAD
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
=======
import java.util.List;

public interface OrdersWebService {
	
	public OrderResponse getUnbilledOrders(List<Integer> userIds);
>>>>>>> d6f3c401b90ee7291d0cce20d68feb42974894ac

public class OrdersWebService {
		
	private static final int RESPONSE_OK = 1;

	public OrderResponse getUnbilledOrders(List<Integer> userIds) {
		OrderResponse response = new OrderResponse();
		
		response.setResponseCode(RESPONSE_OK);
		response.setOrders(createRandomOrders(userIds));
		
		return response;
	}

	private List<Order> createRandomOrders(List<Integer> userIds) {
		List<Order> orderList = new ArrayList<Order>();
		for (int userId : userIds) {
			for (int i=0; i<2; i++) {
				Order order = new Order();
				order.setAmount(new BigDecimal("123"));
				order.setCustomerId(userId);
				orderList.add(order);
			}
		}
		return orderList;
	}
}
