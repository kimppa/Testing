package com.github.kimppa.exercises.refactor.after;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.kimppa.exercises.refactor.Invoice;
import com.github.kimppa.exercises.refactor.Order;

public class Invoicer {
	
	private OrderFetcher orderFetcher;
	private OrderGrouper orderGrouper;
	private InvoiceCreator invoiceCreator;

	public List<Invoice> createInvoicesForUsers(List<Integer> userIds) {
		List<Order> orders = getUnbilledOrders(userIds);
		
		Map<Integer, List<Order>> ordersPerUser = groupOrderByUser(orders);
		
		List<Invoice> invoices = invoiceOrders(ordersPerUser);

		return invoices;
	}

	private List<Invoice> invoiceOrders(Map<Integer, List<Order>> ordersPerUser) {
		List<Invoice> invoices = new ArrayList<Invoice>();
		
		for (Entry<Integer, List<Order>> ordersForUser : ordersPerUser.entrySet()) {
			Invoice invoice = createInvoice(ordersForUser);
			invoices.add(invoice);
		}
		
		return invoices;
	}

	private Invoice createInvoice(Entry<Integer, List<Order>> ordersForUser) {
		int userId = ordersForUser.getKey();
		List<Order> orderList = ordersForUser.getValue();
		Invoice invoice = getInvoiceCreator().createInvoiceForOrders(userId, orderList);
		return invoice;
	}

	private Map<Integer, List<Order>> groupOrderByUser(List<Order> orders) {
		Map<Integer, List<Order>> ordersPerUser = createOrderGrouper(orders).groupByUsers();
		return ordersPerUser;
	}

	private List<Order> getUnbilledOrders(List<Integer> userIds) {
		List<Order> orders = getOrderFetcher().getUnbillerOrders(userIds);
		return orders;
	}

	OrderGrouper createOrderGrouper(List<Order> orders) {
		if (orderGrouper == null) {
			orderGrouper = new OrderGrouper(orders);
		}
		return orderGrouper;
	}
	
	private InvoiceCreator getInvoiceCreator() {
		if (invoiceCreator == null) {
			invoiceCreator = new InvoiceCreator();
		}
		return invoiceCreator;
	}

	private OrderFetcher getOrderFetcher() {
		if (orderFetcher == null) {
			orderFetcher = new OrderFetcher();
		}
		return orderFetcher;
	}

}
