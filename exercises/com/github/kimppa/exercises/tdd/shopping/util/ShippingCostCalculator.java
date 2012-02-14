package com.github.kimppa.exercises.tdd.shopping.util;

import java.math.BigDecimal;

public class ShippingCostCalculator {
	
	public BigDecimal getShippingCosts(BigDecimal purchaseTotal) {
		if(purchaseTotal.compareTo(new BigDecimal("200")) >= 0) {
			return new BigDecimal("0");
		}
	
		return new BigDecimal("20");
	}

}
