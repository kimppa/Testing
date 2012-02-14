package com.github.kimppa.exercises.tdd.shopping.util;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

import com.github.kimppa.exercises.tdd.shopping.Product;

public class DiscountCalculatorTests {
	
	@Test
	public void getDiscountPercent_unitPrice101quantity11_discountIs10Percent() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("101"));
		
		Assert.assertEquals(new BigDecimal("0.1").compareTo(new DiscountCalculator().getDiscountPercent(product, 11)), 0);
	}
	
	@Test
	public void getDiscountPercent_unitPrice100quantity10_discountIs10Percent() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("100"));
		
		Assert.assertEquals(new BigDecimal("0.1").compareTo(new DiscountCalculator().getDiscountPercent(product, 10)), 0);
	}
	
	@Test
	public void getDiscountPercent_unitPrice100quantity9_discountIs5Percent() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("100"));
		
		Assert.assertEquals(new BigDecimal("0.05").compareTo(new DiscountCalculator().getDiscountPercent(product, 9)), 0);
	}
	
	@Test
	public void getDiscountPercent_unitPrice101quantity4_discountIs0Percent() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("101"));
		
		Assert.assertEquals(new BigDecimal("0.0").compareTo(new DiscountCalculator().getDiscountPercent(product, 4)), 0);
	}
	
	@Test
	public void getDiscountPercent_unitPrice51quantity11_discountIs5Percent() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("51"));
		
		Assert.assertEquals(new BigDecimal("0.05").compareTo(new DiscountCalculator().getDiscountPercent(product, 11)), 0);
	}
	
	@Test
	public void getDiscountPercent_unitPrice51quantity9_discountIs5Percent() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("51"));
		
		Assert.assertEquals(new BigDecimal("0.05").compareTo(new DiscountCalculator().getDiscountPercent(product, 9)), 0);
	}
	
	@Test
	public void getDiscountPercent_unitPrice50quantity9_discountIs5Percent() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("50"));
		
		Assert.assertEquals(new BigDecimal("0.05").compareTo(new DiscountCalculator().getDiscountPercent(product, 9)), 0);
	}
	
	@Test
	public void getDiscountPercent_unitPrice50quantity5_discountIs5Percent() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("50"));
		
		Assert.assertEquals(new BigDecimal("0.05").compareTo(new DiscountCalculator().getDiscountPercent(product, 5)), 0);
	}
	
	@Test
	public void getDiscountPercent_unitPrice50quantity4_discountIs0Percent() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("50"));
		
		Assert.assertEquals(new BigDecimal("0.0").compareTo(new DiscountCalculator().getDiscountPercent(product, 4)), 0);
	}
	
	@Test
	public void getDiscountPercent_unitPrice49quantity4_discountIs0Percent() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("49"));
		
		Assert.assertEquals(new BigDecimal("0.0").compareTo(new DiscountCalculator().getDiscountPercent(product, 4)), 0);
	}
	
	@Test
	public void getDiscountPercent_unitPrice49quantity12_discountIs0Percent() {
		Product product = new Product();
		product.setUnitPrice(new BigDecimal("49"));
		
		Assert.assertEquals(new BigDecimal("0.0").compareTo(new DiscountCalculator().getDiscountPercent(product, 12)), 0);
	}

}
