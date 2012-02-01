package com.github.kimppa.exercises.refactor.after;

import java.util.ArrayList;
import java.util.List;

import com.github.kimppa.exercises.refactor.Invoice;
import com.github.kimppa.exercises.refactor.OrdersWebService;


public class InvoiceProcessor {
	
	private OrdersWebService ordersWebService;
	
	public List<Invoice> createInvoicesForUser(int userId) {
		List<Invoice> invoices = new ArrayList<Invoice>();
		
		// Get data from ws
		
		// Handle timeouts and response codes
		
		// create invoice objects
		
		return invoices;
	}

}
