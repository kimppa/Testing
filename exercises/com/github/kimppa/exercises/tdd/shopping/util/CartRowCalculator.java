package com.github.kimppa.exercises.tdd.shopping.util;

import java.math.BigDecimal;

import com.github.kimppa.exercises.tdd.shopping.Product;

public class CartRowCalculator {

	private DiscountCalculator discountCalculator;

	/**
	 * Calculates the sum of the product*quantity taking into account any
	 * possible discounts.
	 * 
	 * @param product
	 * @param quantity
	 * @return
	 */
	public BigDecimal getRowTotal(Product product, int quantity) {
		BigDecimal totalPrice = product.getUnitPrice().multiply(
				new BigDecimal(quantity));
		totalPrice = totalPrice.subtract(totalPrice
				.multiply(getDiscountCalculator().getDiscountPercent(product,
						quantity)));
		return totalPrice;
	}

	private DiscountCalculator getDiscountCalculator() {
		if (discountCalculator == null) {
			discountCalculator = new DiscountCalculator();
		}

		return discountCalculator;
	}

}
