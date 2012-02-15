package com.github.kimppa.exercises.tdd.shopping.util;

import java.math.BigDecimal;
import java.util.Collection;

import com.github.kimppa.exercises.tdd.shopping.CartRow;

public class CartCalculator {

	/**
	 * Calculates the total sum of all rows in the cart.
	 * 
	 * @param cartRows
	 * @return
	 */
	public BigDecimal getRowsTotal(Collection<CartRow> cartRows) {
		BigDecimal total = new BigDecimal("0");
		for (CartRow row : cartRows) {
			total = total.add(row.getRowTotal());
		}

		return total;
	}

}
