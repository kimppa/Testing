package com.github.kimppa.exercises.refactor.after;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.kimppa.exercises.refactor.Invoice;
import com.github.kimppa.exercises.refactor.Order;
import com.github.kimppa.exercises.refactor.OrderResponse;
import com.github.kimppa.exercises.refactor.OrderWebServiceImpl;
import com.github.kimppa.exercises.refactor.OrdersWebService;

public class Invoicer {
	private static final int RESPONSE_CODE_OK = 1;
	private OrdersWebService ordersWebService = new OrderWebServiceImpl();

	public Collection<Invoice> createInvoicesForUsers(List<Integer> userIds) {
		OrderResponse response;
		// Get data from ws
		try {
			response = ordersWebService.getUnbilledOrders(userIds);

			// Handle response codes
			handleResponse(response);
		} catch (Exception e) {
			// an exception occurred, e.g. timeout
			return null;
		}
		
		// Map between customers and their invoices
		Map<Integer, Invoice> invoices = new HashMap<Integer, Invoice>();
		if (response.getOrders() != null) {
			// Loop through each order and process it
			for (Order order : response.getOrders()) {
				processOrder(invoices, order);
			}
		}

		return invoices.values();
	}

	/**
	 * Checks if we already have an invoice for the customer whose order we are
	 * processing. If an invoice already exists, then add this order to the
	 * existing invoice, otherwise create a new invoice.
	 * 
	 * @param invoices
	 * @param order
	 */
	private void processOrder(Map<Integer, Invoice> invoices, Order order) {
		Invoice invoice;
		if(invoices.containsKey(order.getCustomerId())) {
			invoice = invoices.get(order.getCustomerId());
		} else {
			invoice = new Invoice();
			invoice.setCustomerId(order.getCustomerId());
			invoices.put(order.getCustomerId(), invoice);
		}
		
		invoice.getOrders().add(order);
		invoice.setAmount(invoice.getAmount().add(order.getAmount()));
	}

	private void handleResponse(OrderResponse response) {
		if (response == null) {
			throw new RuntimeException("Response was null");
		}

		// response codes 0=error / 1=ok
		if (response.getResponseCode() != RESPONSE_CODE_OK) {
			throw new RuntimeException("Request failed");
		}
	}
}
