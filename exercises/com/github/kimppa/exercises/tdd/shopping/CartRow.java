package com.github.kimppa.exercises.tdd.shopping;

import java.math.BigDecimal;

import com.github.kimppa.exercises.tdd.shopping.util.CartRowCalculator;

public class CartRow {
	
	private Product product;
	
	private int quantity;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getRowTotal() {
		return new CartRowCalculator().getRowTotal(getProduct(), getQuantity());
	}
	

}
