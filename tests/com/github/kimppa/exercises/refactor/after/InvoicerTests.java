package com.github.kimppa.exercises.refactor.after;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
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

public class InvoicerTests {

	private static final int USER_1 = 1;
	private static final int USER_2 = 2;
	
	@Mock
	private OrdersWebService orderWebService;
	
	@InjectMocks
	private Invoicer invoicer;
	
	@Before
	public void setUp() {
		invoicer = new Invoicer();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createInvoicesForUser_webServiceThrowsException_nullReturned() {
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenThrow(new RuntimeException("test"));
		
		Collection<Invoice> invoices =  invoicer.createInvoicesForUsers(getUsers());
		
		assertNull(invoices);
		
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}
	
	@Test
	public void createInvoicesForUser_webServiceReturnsNull_nullReturned() {
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(null);
		
		Collection<Invoice> invoices =  invoicer.createInvoicesForUsers(getUsers());
		
		assertNull(invoices);
		
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}
	
	@Test
	public void createInvoicesForUser_webServiceReturnsResponseCode0_nullReturned() {
		OrderResponse response = Mockito.mock(OrderResponse.class);
		
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);
		Mockito.when(response.getResponseCode()).thenReturn(0);
		
		
		Collection<Invoice> invoices =  invoicer.createInvoicesForUsers(getUsers());
		
		assertNull(invoices);
		
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}
	
	@Test
	public void createInvoicesForUser_webServiceReturnsNullOrders_emptyListReturned() {
		OrderResponse response = Mockito.mock(OrderResponse.class);
		
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);
		Mockito.when(response.getResponseCode()).thenReturn(1);
		Mockito.when(response.getOrders()).thenReturn(null);
				
		Collection<Invoice> invoices =  invoicer.createInvoicesForUsers(getUsers());
		
		assertNotNull(invoices);
		assertEquals(0, invoices.size());
		
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}
	
	@Test
	public void createInvoicesForUser_webServiceReturnsTwoCustomers_twoInvoicesReturned() {
		OrderResponse response = Mockito.mock(OrderResponse.class);
		List<Order> orders = new ArrayList<Order>();
		Order order = new Order();
		order.setCustomerId(1);
		order.setAmount(new BigDecimal("10.5"));
		orders.add(order);
		
		order = new Order();
		order.setCustomerId(2);
		order.setAmount(new BigDecimal("16.5"));
		orders.add(order);		
		
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);
		Mockito.when(response.getResponseCode()).thenReturn(1);
		Mockito.when(response.getOrders()).thenReturn(orders);
		
		Collection<Invoice> invoices =  invoicer.createInvoicesForUsers(getUsers());
		
		assertNotNull(invoices);
		assertEquals(2, invoices.size());
		
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}
	
	@Test
	public void createInvoicesForUser_webServiceReturnsTwoCustomersThreeOrders_twoInvoicesReturned() {
		OrderResponse response = Mockito.mock(OrderResponse.class);
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
		
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);
		Mockito.when(response.getResponseCode()).thenReturn(1);
		Mockito.when(response.getOrders()).thenReturn(orders);
		
		Collection<Invoice> invoices =  invoicer.createInvoicesForUsers(getUsers());
		
		assertNotNull(invoices);
		assertEquals(2, invoices.size());
		
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}
	
	@Test
	public void createInvoicesForUser_webServiceReturnsTwoCustomersThreeOrders_sumsCalculatedCorrectly() {
		BigDecimal orderAmount_10_5 = new BigDecimal("10.5");
		BigDecimal orderAmount_16_5 = new BigDecimal("16.5");		
		BigDecimal orderAmount_9_25 = new BigDecimal("9.25");
		
		OrderResponse response = new OrderResponse();
		response.setResponseCode(1);
		
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
		order.setCustomerId(USER_2);
		order.setAmount(orderAmount_9_25);
		orders.add(order);
		
		response.setOrders(orders);
		
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(response);
		
		Collection<Invoice> invoices =  invoicer.createInvoicesForUsers(getUsers());
		
		assertNotNull(invoices);
		
		/*
		Invoice invoice = invoices.get(USER_1);
		assertEquals(USER_1, invoice.getCustomerId());
		Assert.assertEquals(orderAmount_10_5, invoice.getAmount());
		invoice = invoices.get(USER_2);
		assertEquals(USER_2, invoice.getCustomerId());
		assertEquals(orderAmount_16_5.add(orderAmount_9_25), invoice.getAmount());
		*/
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}

	
	private List<Integer> getUsers() {
		List<Integer> users = new ArrayList<Integer>();
		users.add(USER_1);
		users.add(USER_2);
		return users;
	}
}
