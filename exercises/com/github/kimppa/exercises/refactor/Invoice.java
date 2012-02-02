package com.github.kimppa.exercises.refactor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
	
	private int customerId;
	
	private BigDecimal amount = new BigDecimal(0);
	
	private List<Order> orders = new ArrayList<Order>();

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
