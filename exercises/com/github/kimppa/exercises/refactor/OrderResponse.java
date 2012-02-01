package com.github.kimppa.exercises.refactor;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
	
	private int responseCode;
	
	private List<Order> orders = new ArrayList<Order>();

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
