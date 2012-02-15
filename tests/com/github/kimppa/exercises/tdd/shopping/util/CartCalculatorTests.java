package com.github.kimppa.exercises.tdd.shopping.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import com.github.kimppa.exercises.tdd.shopping.CartRow;

public class CartCalculatorTests {

	@Test
	public void getRowsTotal_noRows_returns0() {
		BigDecimal sum = new CartCalculator().getRowsTotal(new ArrayList<CartRow>());
		Assert.assertEquals(0, sum.compareTo(new BigDecimal("0")));
	}
	
	@Test
	public void getRowsTotal_rowTotals100And150_50_returns250_50() {
		List<CartRow> rows = new ArrayList<CartRow>();
		
		CartRow row1 = Mockito.mock(CartRow.class);
		Mockito.when(row1.getRowTotal()).thenReturn(new BigDecimal("100"));
		rows.add(row1);
		
		CartRow row2 = Mockito.mock(CartRow.class);
		Mockito.when(row2.getRowTotal()).thenReturn(new BigDecimal("150.50"));
		rows.add(row2);
		
		BigDecimal sum = new CartCalculator().getRowsTotal(rows);
		Assert.assertEquals(0, sum.compareTo(new BigDecimal("250.5")));
	}
	
}
