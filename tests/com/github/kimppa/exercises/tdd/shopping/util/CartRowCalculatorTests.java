package com.github.kimppa.exercises.tdd.shopping.util;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.kimppa.exercises.tdd.shopping.Product;

public class CartRowCalculatorTests {

	@Mock
	private DiscountCalculator discountCalculator;

	@InjectMocks
	private CartRowCalculator cartRowCalculator;

	@Before
	public void setUp() {
		cartRowCalculator = new CartRowCalculator();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getRowTotal_rowSumIs1500DiscountPercentageIs10_returns1350() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("150"));
		
		Mockito.when(
				discountCalculator.getDiscountPercent(
						product, 10))
				.thenReturn(new BigDecimal("0.10"));
		BigDecimal total = cartRowCalculator.getRowTotal(product, 10);
		
		Assert.assertEquals("Discounted price was " + total, 0, total.compareTo(new BigDecimal("1350")));
		Mockito.verify(discountCalculator).getDiscountPercent(product, 10);
	}

	@Test
	public void getRowTotal_rowSumIs1500DiscountPercentageIs0_returns1500() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("150"));
		
		Mockito.when(
				discountCalculator.getDiscountPercent(
						product, 10))
				.thenReturn(new BigDecimal("0"));
		BigDecimal total = cartRowCalculator.getRowTotal(product, 10);
		
		Assert.assertEquals("Discounted price was " + total, 0, total.compareTo(new BigDecimal("1500")));
		Mockito.verify(discountCalculator).getDiscountPercent(product, 10);
	}

}
