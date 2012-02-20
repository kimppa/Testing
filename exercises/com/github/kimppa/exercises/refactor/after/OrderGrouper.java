package com.github.kimppa.exercises.refactor.after;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.kimppa.exercises.refactor.Order;

public class OrderGrouper {

	Map<Integer, List<Order>> ordersPerUser;
	List<Order> orders;
	
	public OrderGrouper(List<Order> orders) {
		this.orders = orders;
		
		ordersPerUser = new HashMap<Integer, List<Order>>();
	}
	
	public Map<Integer, List<Order>> groupByUsers() {
		for (Order order : orders) {
			List<Order> orders = getOrCreateOrderListForUser(order.getCustomerId());
			orders.add(order);
		}
		return ordersPerUser;
	}

	private List<Order> getOrCreateOrderListForUser(int customerId) {
		if (!ordersPerUser.containsKey(customerId)) {
			List<Order> orders = new ArrayList<Order>();
			ordersPerUser.put(customerId, orders);
		}
		return ordersPerUser.get(customerId);
	}

}
