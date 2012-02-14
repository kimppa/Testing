package com.github.kimppa.exercises.tdd.shopping.util;

import java.math.BigDecimal;

import com.github.kimppa.exercises.tdd.shopping.Product;

public class DiscountCalculator {

	public BigDecimal getDiscountPercent(Product product, int quantity) {
		if (isEligableFor10PercentageDiscount(product, quantity)) {
			return new BigDecimal("0.10");
		}

		if (isEligableFor5PercentageDiscount(product, quantity)) {
			return new BigDecimal("0.05");
		}

		return new BigDecimal(0);
	}

	private boolean isEligableFor5PercentageDiscount(Product product,
			int quantity) {
		return product.getUnitPrice().compareTo(new BigDecimal(50)) >= 0
				&& quantity >= 5;
	}

	private boolean isEligableFor10PercentageDiscount(Product product,
			int quantity) {
		return product.getUnitPrice().compareTo(new BigDecimal(100)) >= 0
				&& quantity >= 10;
	}

}
