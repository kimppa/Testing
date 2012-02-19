package com.github.kimppa.exercises.refactor.after;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.kimppa.exercises.refactor.Invoice;
import com.github.kimppa.exercises.refactor.Order;

public class InvoiceCreatorTest {

	private static final int USER_1 = 1;
	private static final BigDecimal orderAmount1 = new BigDecimal("11.11");
	private static final BigDecimal orderAmount2 = new BigDecimal("22.22");
	
	InvoiceCreator invoiceCreator;
	
	@Before
	public void setUp() {
		invoiceCreator = new InvoiceCreator();
	}
	
	@Test
	public void createInvoiceForOrders_twoOrders_invoiceReturned() {
		Invoice invoice = invoiceCreator.createInvoiceForOrders(USER_1, createOrders());

		BigDecimal expectedAmount = orderAmount1.add(orderAmount2);
		assertEquals(USER_1, invoice.getCustomerId());
		assertEquals(expectedAmount, invoice.getAmount());
	}

	private List<Order> createOrders() {
		List<Order> orders = new ArrayList<Order>();
		
		Order order = new Order();
		order.setAmount(orderAmount1);
		order.setCustomerId(USER_1);
		orders.add(order);
		
		order = new Order();
		order.setAmount(orderAmount2);
		order.setCustomerId(USER_1);
		orders.add(order);
		
		return orders;
	}

}
