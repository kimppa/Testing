package com.github.kimppa.exercises.refactor.before;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.kimppa.exercises.refactor.Invoice;
import com.github.kimppa.exercises.refactor.Order;
import com.github.kimppa.exercises.refactor.OrderResponse;
import com.github.kimppa.exercises.refactor.OrdersWebService;

public class InvoiceProcessorTests {

	@Mock
	private OrdersWebService orderWebService;

	@InjectMocks
	private InvoiceProcessor invoiceProcessor;

	@Before
	public void setUp() {
		invoiceProcessor = new InvoiceProcessor();
		MockitoAnnotations.initMocks(this);
	}

	private static final Integer USER_1 = 1;
	private static final Integer USER_2 = 2;
	private List<Integer> getUsers() {
		List<Integer> users = new ArrayList<Integer>();
		users.add(USER_1);
		users.add(USER_2);
		return users;
	}
	
	@Test
	public void createInvoicesForUser_webServiceThrowsException_nullReturned() {
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenThrow(
				new RuntimeException("test"));

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNull(invoices);

		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	@Test
	public void createInvoicesForUser_webServiceReturnsNull_nullReturned() {
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(null);

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNull(invoices);

		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	@Test
	public void createInvoicesForUser_webServiceReturnsResponseCode0_nullReturned() {
		OrderResponse response = new OrderResponse();
		response.setResponseCode(0);

		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNull(invoices);

		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	@Test
	public void createInvoicesForUser_webServiceReturnsNullOrders_emptyListReturned() {
		OrderResponse response = new OrderResponse();
		response.setResponseCode(1);
		response.setOrders(null);

		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNotNull(invoices);
		Assert.assertEquals(0, invoices.size());

		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	@Test
	public void createInvoicesForUser_webServiceReturnsTwoCustomers_twoInvoicesReturned() {
		OrderResponse response = new OrderResponse();
		List<Order> orders = new ArrayList<Order>();
		Order order = new Order();
		order.setCustomerId(1);
		order.setAmount(new BigDecimal("10.5"));
		orders.add(order);

		order = new Order();
		order.setCustomerId(2);
		order.setAmount(new BigDecimal("16.5"));
		orders.add(order);

		response.setResponseCode(1);
		response.setOrders(orders);

		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNotNull(invoices);
		Assert.assertEquals(2, invoices.size());

		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	@Test
	public void createInvoicesForUser_webServiceReturnsTwoCustomersThreeOrders_twoInvoicesReturned() {
		OrderResponse response = new OrderResponse();
		List<Order> orders = new ArrayList<Order>();
		Order order = new Order();
		order.setCustomerId(1);
		order.setAmount(new BigDecimal("10.5"));
		orders.add(order);

		order = new Order();
		order.setCustomerId(2);
		order.setAmount(new BigDecimal("16.5"));
		orders.add(order);

		order = new Order();
		order.setCustomerId(2);
		order.setAmount(new BigDecimal("9.25"));
		orders.add(order);

		response.setResponseCode(1);
		response.setOrders(orders);

		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNotNull(invoices);
		Assert.assertEquals(2, invoices.size());

		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	@Test
	public void createInvoicesForUser_webServiceReturnsTwoCustomersThreeOrders_sumsCalculatedCorrectly() {
		OrderResponse response = new OrderResponse();
		List<Order> orders = new ArrayList<Order>();
		Order order = new Order();
		order.setCustomerId(1);
		order.setAmount(new BigDecimal("10.5"));
		orders.add(order);

		order = new Order();
		order.setCustomerId(2);
		order.setAmount(new BigDecimal("16.5"));
		orders.add(order);

		order = new Order();
		order.setCustomerId(2);
		order.setAmount(new BigDecimal("9.25"));
		orders.add(order);

		response.setResponseCode(1);
		response.setOrders(orders);

		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNotNull(invoices);
		Invoice invoice = invoices.get(0);
		Assert.assertEquals(1, invoice.getCustomerId());
		Assert.assertEquals(new BigDecimal("10.5"), invoice.getAmount());
		invoice = invoices.get(1);
		Assert.assertEquals(2, invoice.getCustomerId());
		Assert.assertEquals(new BigDecimal("25.75"), invoice.getAmount());

		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

}
