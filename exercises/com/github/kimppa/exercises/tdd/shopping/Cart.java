package com.github.kimppa.exercises.tdd.shopping;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.github.kimppa.exercises.tdd.shopping.util.ShippingCostCalculator;

public class Cart {
	
	private Map<Product, CartRow> cartRows = new HashMap<Product, CartRow>();
	
	public void addProduct(Product product) {
		addProduct(product, 1);
	}
	
	public void addProduct(Product product, int quantity) {
		CartRow row;
		if(cartRows.containsKey(product)) {
			row = cartRows.get(product);
		} else {
			row = new CartRow();
			row.setProduct(product);
			cartRows.put(product, row);
		}
		
		row.setQuantity(row.getQuantity()+quantity);
	}
	
	public void setProductQuantity(Product product, int quantity) {
		cartRows.get(product).setQuantity(quantity);
	}
	
	public void removeProduct(Product product) {
		cartRows.remove(product);
	}
	
	public Collection<CartRow> getRows() {
		return cartRows.values();
	}
	
	public BigDecimal getRowsTotal() {
		BigDecimal total = new BigDecimal("0");
		for(CartRow row : cartRows.values()) {
			total = total.add(row.getRowTotal());
		}
		
		return total;
	}
	
	public BigDecimal getShippingCosts() {
		return new ShippingCostCalculator().getShippingCosts(getRowsTotal());
	}
	
	public BigDecimal getTotal() {
		return getRowsTotal().add(getShippingCosts());
	}
	
}
