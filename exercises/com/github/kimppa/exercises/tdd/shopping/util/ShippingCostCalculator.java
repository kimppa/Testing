package com.github.kimppa.exercises.tdd.shopping.util;

import java.math.BigDecimal;

public class ShippingCostCalculator {

	/**
	 * Calculates the shipping costs for the purchase.
	 * 
	 * @param purchaseTotal
	 *            The total sum of the purchase excluding shipping costs.
	 * @return
	 */
	public BigDecimal getShippingCosts(BigDecimal purchaseTotal) {
		if (purchaseTotal.compareTo(new BigDecimal("200")) >= 0) {
			return new BigDecimal("0");
		}

		return new BigDecimal("20");
	}

}
