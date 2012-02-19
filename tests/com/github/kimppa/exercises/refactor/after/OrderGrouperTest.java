package com.github.kimppa.exercises.refactor.after;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.github.kimppa.exercises.refactor.Order;

public class OrderGrouperTest {

	private static final int USER_1 = 1;
	private static final int USER_2 = 2;
	private static final int USER_3 = 3;
	OrderGrouper orderGrouper;
	
	@Test
	public void groupByUsers_emptyOrderList_emptyMapReturned() {		
		orderGrouper = new OrderGrouper(Collections.<Order>emptyList());
		
		Map<Integer, List<Order>> ordersPerUser = orderGrouper.groupByUsers();
		
		assertNotNull(ordersPerUser);
		assertEquals(0, ordersPerUser.size());
	}
	
	@Test
	public void groupByUsers_shuffledOrderList_ordersGroupedByUser() {		
		List<Order> ordersShuffled = createTwoOrdersForUserNumberOfUsersInRandomOrder(USER_1, USER_2, USER_3);
		orderGrouper = new OrderGrouper(ordersShuffled);
		
		Map<Integer, List<Order>> ordersPerUser = orderGrouper.groupByUsers();
		
		assertNotNull(ordersPerUser);
		assertContainsOrdersForUser(USER_1, ordersPerUser.get(USER_1));
		assertContainsOrdersForUser(USER_2, ordersPerUser.get(USER_2));
		assertContainsOrdersForUser(USER_3, ordersPerUser.get(USER_3));
	}

	private void assertContainsOrdersForUser(int expectedUserId, List<Order> orders) {
		assertFalse(orders.isEmpty());
		
		for (Order order : orders) {
			assertEquals(expectedUserId, order.getCustomerId());
		}
	}

	private List<Order> createTwoOrdersForUserNumberOfUsersInRandomOrder(Integer... userIds) {
		List<Order> orders = new ArrayList<Order>();
		for (Integer userId : userIds) {
			Order order = new Order();
			order.setAmount(new BigDecimal("10.10"));
			order.setCustomerId(userId);
			orders.add(order);
			
			order = new Order();
			order.setAmount(new BigDecimal("10.10"));
			order.setCustomerId(userId);
			orders.add(order);
		}
		Collections.shuffle(orders);
		return orders;
	}

}
