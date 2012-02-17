package com.github.kimppa.exercises.refactor.before;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
<<<<<<< HEAD
	
	private static final int USER_1 = 1;
	private static final int USER_2 = 2;
=======
>>>>>>> d6f3c401b90ee7291d0cce20d68feb42974894ac

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
<<<<<<< HEAD
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenThrow(new RuntimeException("test"));
		
		Map<Integer, Invoice> invoices =  invoiceProcessor.createInvoicesForUsers(getUsers());
		
		Assert.assertNull(invoices);
		
=======
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenThrow(
				new RuntimeException("test"));

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNull(invoices);

>>>>>>> d6f3c401b90ee7291d0cce20d68feb42974894ac
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	@Test
	public void createInvoicesForUser_webServiceReturnsNull_nullReturned() {
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(null);
<<<<<<< HEAD
		
		Map<Integer, Invoice> invoices =  invoiceProcessor.createInvoicesForUsers(getUsers());
		
		Assert.assertNull(invoices);
		
=======

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNull(invoices);

>>>>>>> d6f3c401b90ee7291d0cce20d68feb42974894ac
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	@Test
	public void createInvoicesForUser_webServiceReturnsResponseCode0_nullReturned() {
<<<<<<< HEAD
		OrderResponse response = Mockito.mock(OrderResponse.class);
		
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);
		Mockito.when(response.getResponseCode()).thenReturn(0);
		
		
		Map<Integer, Invoice> invoices =  invoiceProcessor.createInvoicesForUsers(getUsers());
		
		Assert.assertNull(invoices);
		
=======
		OrderResponse response = new OrderResponse();
		response.setResponseCode(0);

		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNull(invoices);

>>>>>>> d6f3c401b90ee7291d0cce20d68feb42974894ac
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	@Test
	public void createInvoicesForUser_webServiceReturnsNullOrders_emptyListReturned() {
<<<<<<< HEAD
		OrderResponse response = Mockito.mock(OrderResponse.class);
		
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);
		Mockito.when(response.getResponseCode()).thenReturn(1);
		Mockito.when(response.getOrders()).thenReturn(null);
				
		Map<Integer, Invoice> invoices =  invoiceProcessor.createInvoicesForUsers(getUsers());
		
		Assert.assertNotNull(invoices);
		Assert.assertEquals(0, invoices.size());
		
=======
		OrderResponse response = new OrderResponse();
		response.setResponseCode(1);
		response.setOrders(null);

		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNotNull(invoices);
		Assert.assertEquals(0, invoices.size());

>>>>>>> d6f3c401b90ee7291d0cce20d68feb42974894ac
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
<<<<<<< HEAD
		orders.add(order);		
		
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);
		Mockito.when(response.getResponseCode()).thenReturn(1);
		Mockito.when(response.getOrders()).thenReturn(orders);
		
		Map<Integer, Invoice> invoices =  invoiceProcessor.createInvoicesForUsers(getUsers());
		
		Assert.assertNotNull(invoices);
		Assert.assertEquals(2, invoices.size());
		
=======
		orders.add(order);

		response.setResponseCode(1);
		response.setOrders(orders);

		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNotNull(invoices);
		Assert.assertEquals(2, invoices.size());

>>>>>>> d6f3c401b90ee7291d0cce20d68feb42974894ac
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
<<<<<<< HEAD
		orders.add(order);		
		
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);
		Mockito.when(response.getResponseCode()).thenReturn(1);
		Mockito.when(response.getOrders()).thenReturn(orders);
		
		Map<Integer, Invoice> invoices =  invoiceProcessor.createInvoicesForUsers(getUsers());
		
		Assert.assertNotNull(invoices);
		Assert.assertEquals(2, invoices.size());
		
=======
		orders.add(order);

		response.setResponseCode(1);
		response.setOrders(orders);

		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);

		List<Invoice> invoices = invoiceProcessor.createInvoicesForUser(1);

		Assert.assertNotNull(invoices);
		Assert.assertEquals(2, invoices.size());

>>>>>>> d6f3c401b90ee7291d0cce20d68feb42974894ac
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	@Test
	public void createInvoicesForUser_webServiceReturnsTwoCustomersThreeOrders_sumsCalculatedCorrectly() {
<<<<<<< HEAD
		BigDecimal orderAmount_10_5 = new BigDecimal("10.5");
		BigDecimal orderAmount_16_5 = new BigDecimal("16.5");		
		BigDecimal orderAmount_9_25 = new BigDecimal("9.25");
		
		OrderResponse response = new OrderResponse();
		response.setResponseCode(1);
		
=======
		OrderResponse response = new OrderResponse();
>>>>>>> d6f3c401b90ee7291d0cce20d68feb42974894ac
		List<Order> orders = new ArrayList<Order>();
		Order order = new Order();
		order.setCustomerId(USER_1);

		order.setAmount(orderAmount_10_5);
		orders.add(order);

		order = new Order();
		order.setCustomerId(USER_2);
		order.setAmount(orderAmount_16_5);
		orders.add(order);

		order = new Order();
<<<<<<< HEAD
		order.setCustomerId(USER_2);
		order.setAmount(orderAmount_9_25);
		orders.add(order);
		
		response.setOrders(orders);
		
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);
		
		Map<Integer, Invoice> invoices =  invoiceProcessor.createInvoicesForUsers(getUsers());
		
		Assert.assertNotNull(invoices);
		Invoice invoice = invoices.get(USER_1);
		Assert.assertEquals(USER_1, invoice.getCustomerId());
		Assert.assertEquals(orderAmount_10_5, invoice.getAmount());
		invoice = invoices.get(USER_2);
		Assert.assertEquals(USER_2, invoice.getCustomerId());
		Assert.assertEquals(orderAmount_16_5.add(orderAmount_9_25), invoice.getAmount());
		
=======
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

>>>>>>> d6f3c401b90ee7291d0cce20d68feb42974894ac
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	
	private List<Integer> getUsers() {
		List<Integer> users = new ArrayList<Integer>();
		users.add(USER_1);
		users.add(USER_2);
		return users;
	}
}
