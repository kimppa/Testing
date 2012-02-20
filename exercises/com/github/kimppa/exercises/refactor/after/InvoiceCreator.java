package com.github.kimppa.exercises.refactor.after;

import java.math.BigDecimal;
import java.util.List;

import com.github.kimppa.exercises.refactor.Invoice;
import com.github.kimppa.exercises.refactor.Order;

public class InvoiceCreator {

	public Invoice createInvoiceForOrders(int userId, List<Order> orders) {
		Invoice invoice = new Invoice();
		invoice.setCustomerId(userId);
		invoice.setOrders(orders);
		
		BigDecimal amount = calculateAmount(orders);
		invoice.setAmount(amount);
		
		return invoice;
	}

	private BigDecimal calculateAmount(List<Order> orders) {
		BigDecimal amount = BigDecimal.ZERO;
		for (Order order : orders) {
			amount = amount.add(order.getAmount());
		}
		return amount;
	}

}
