package com.github.kimppa.exercises.tdd.shopping.util;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class ShippingCostCalculatorTests {
	
	@Test
	public void getShippingCosts_totalPurhcaseUnder200_returns20() {
		Assert.assertEquals(new BigDecimal("20"), new ShippingCostCalculator().getShippingCosts(new BigDecimal("199")));
	}
	
	@Test
	public void getShippingCosts_totalPurhcaseExactly200_returns0() {
		Assert.assertEquals(new BigDecimal("0"), new ShippingCostCalculator().getShippingCosts(new BigDecimal("200")));
	}
	@Test
	public void getShippingCosts_totalPurhcaseOver200_returns0() {
		Assert.assertEquals(new BigDecimal("0"), new ShippingCostCalculator().getShippingCosts(new BigDecimal("201")));
	}

}
