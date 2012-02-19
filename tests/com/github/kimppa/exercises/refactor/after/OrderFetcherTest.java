package com.github.kimppa.exercises.refactor.after;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.kimppa.exercises.refactor.Order;
import com.github.kimppa.exercises.refactor.OrderResponse;
import com.github.kimppa.exercises.refactor.OrdersWebService;

public class OrderFetcherTest {

	private static final Integer USER_1 = 1;
	private static final Integer USER_2 = 2;
	
	@Mock
	private OrdersWebService orderWebService;
	
	@InjectMocks
	private OrderFetcher orderFetcher;

	@Before
	public void setUp() {
		orderFetcher = new OrderFetcher();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected=RuntimeException.class)
	public void getUnbilledOrders_responseCode0Returned_exceptionThrown() {
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(createOrderResponse(0));
		
		try {
			orderFetcher.getUnbillerOrders(getUsers());
		} finally {
			Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
		}
	}
	
	@Test(expected=RuntimeException.class)
	public void getUnbilledOrders_nullResponseReturned_exceptionThrown() {
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(null);
		
		try {
			orderFetcher.getUnbillerOrders(getUsers());
		} finally {
			Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
		}
	}
	
	@Test
	public void getUnbilledOrders_success_orderListReturned() {
		Mockito.when(orderWebService.getUnbilledOrders(getUsers())).thenReturn(createOrderResponse(1));
		
		orderFetcher.getUnbillerOrders(getUsers());
		Mockito.verify(orderWebService, Mockito.times(1)).getUnbilledOrders(getUsers());
	}
	
	
	private OrderResponse createOrderResponse(int responseCode) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setResponseCode(responseCode);
		orderResponse.setOrders(createOrderList());
		return orderResponse;
	}

	private List<Order> createOrderList() {
		List<Order> orders = new ArrayList<Order>();
		for (int userId : getUsers()) {
			Order order = new Order();
			order.setCustomerId(userId);
			order.setAmount(new BigDecimal("12.34"));
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
