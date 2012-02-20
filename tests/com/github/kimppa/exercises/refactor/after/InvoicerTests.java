package com.github.kimppa.exercises.refactor.after;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.kimppa.exercises.refactor.Invoice;
import com.github.kimppa.exercises.refactor.Order;

public class InvoicerTests {

	private static final int USER_1 = 1;
	private static final int USER_2 = 2;
	private static final int[] userIds = { USER_1, USER_2 };
	
	@Mock OrderFetcher orderFetcher;	
	@Mock OrderGrouper orderGrouper;	
	@Mock InvoiceCreator invoiceCreator;
	
	@InjectMocks Invoicer invoicer;
	
	@Before
	public void setUp() {
		invoicer = Mockito.spy(new Invoicer());
		
		MockitoAnnotations.initMocks(this);
		
		Mockito.doReturn(orderGrouper).when(invoicer).createOrderGrouper(Mockito.anyList());
	}
	
	@Test
	public void createInvoicesForUsers_twoUsersFourOrders_verifyCalls() {
		orderFetcherExpectGetUnbilledOrders();
		orderGrouperExpectGroupByUsers();
		invoiceCreatorExpectInvoiceOrders(USER_1);
		invoiceCreatorExpectInvoiceOrders(USER_2);
		
		List<Invoice> invoices = invoicer.createInvoicesForUsers(getUsers());
		
		Mockito.verify(orderFetcher, Mockito.times(1)).getUnbillerOrders(getUsers());
		Mockito.verify(orderGrouper, Mockito.times(1)).groupByUsers();
		Mockito.verify(invoiceCreator, Mockito.times(1)).createInvoiceForOrders(Mockito.eq(USER_1), Mockito.anyList());
		Mockito.verify(invoiceCreator, Mockito.times(1)).createInvoiceForOrders(Mockito.eq(USER_2), Mockito.anyList());
	}
	
	@Test
	public void createInvoicesForUsers_twoUsersFourOrders_oneInvoicePerUserReturned() {
		orderFetcherExpectGetUnbilledOrders();
		orderGrouperExpectGroupByUsers();
		invoiceCreatorExpectInvoiceOrders(USER_1);
		invoiceCreatorExpectInvoiceOrders(USER_2);
		
		List<Invoice> invoices = invoicer.createInvoicesForUsers(getUsers());
		
		assertEquals(2, invoices.size());
		assertEquals(2, invoices.get(0).getOrders().size());
		assertEquals(2, invoices.get(1).getOrders().size());
	}

	private void invoiceCreatorExpectInvoiceOrders(int userId) {
		Mockito.when(invoiceCreator.createInvoiceForOrders(Mockito.eq(userId), Mockito.anyList())).thenReturn(getInvoiceForUser(userId));
	}

	private void orderGrouperExpectGroupByUsers() {
		Mockito.when(orderGrouper.groupByUsers()).thenReturn(getOrdersGroupedByUser());
	}

	private void orderFetcherExpectGetUnbilledOrders() {
		Mockito.when(orderFetcher.getUnbillerOrders(getUsers())).thenReturn(createTwoOrdersForUsers(USER_1, USER_2));
	}
	
	private Invoice getInvoiceForUser(int userId) {
		List<Order> orders = createTwoOrdersForUsers(userId);
		
		Invoice invoice = new Invoice();
		invoice.setCustomerId(userId);
		invoice.setOrders(orders);
		return invoice;
	}

	private Map<Integer, List<Order>> getOrdersGroupedByUser() {
		Map<Integer, List<Order>> ordersGroupedByUser = new HashMap<Integer, List<Order>>();
		
		ordersGroupedByUser.put(USER_1, createTwoOrdersForUsers(USER_1));
		ordersGroupedByUser.put(USER_2, createTwoOrdersForUsers(USER_2));
		
		return ordersGroupedByUser;
	}

	private List<Order> createTwoOrdersForUsers(Integer... users) {
		List<Order> orders = new ArrayList<Order>();
		for (Integer userId : users) {
			Order order = new Order();
			order.setCustomerId(userId);
			order.setAmount(new BigDecimal("11.22"));
			orders.add(order);
			
			order = new Order();
			order.setCustomerId(userId);
			order.setAmount(new BigDecimal("22.33"));
			orders.add(order);
		}
		return orders;
	}

	private List<Integer> getUsers() {
		List<Integer> users = new ArrayList<Integer>();
		users.add(USER_1);
		users.add(USER_2);
		return users;
	}
}
