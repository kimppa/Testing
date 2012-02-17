package com.github.kimppa.exercises.tdd.shopping;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.github.kimppa.exercises.tdd.shopping.util.CartCalculator;
import com.github.kimppa.exercises.tdd.shopping.util.ShippingCostCalculator;

/**
 * This class represents the content of a shopping cart.
 * 
 * The entire class is implemented using design-by-contract.
 * 
 */
public class Cart {

	private Map<Product, CartRow> cartRows = new HashMap<Product, CartRow>();
	private CartCalculator cartCalculator;
	private ShippingCostCalculator shippingCostCalculator;

	/**
	 * Adds the given product to the shopping cart. If the product already
	 * exists in the cart, then its quantity will be increased by one.
	 * 
	 * @param product
	 */
	public void addProduct(Product product) {
		addProduct(product, 1);
	}

	/**
	 * Adds the given product to the shopping cart. The quantity of purchased
	 * items is given as a parameter. If the product already exists in the cart,
	 * then its quantity will be increased by the given number.
	 * 
	 * @param product
	 * @param quantity
	 */
	public void addProduct(Product product, int quantity) {
		CartRow row;
		if (cartRows.containsKey(product)) {
			row = cartRows.get(product);
		} else {
			row = new CartRow();
			row.setProduct(product);
			cartRows.put(product, row);
		}

		row.setQuantity(row.getQuantity() + quantity);
	}

	/**
	 * Sets a new quantity for the given product.
	 * 
	 * @param product
	 * @param quantity
	 */
	public void setProductQuantity(Product product, int quantity) {
		cartRows.get(product).setQuantity(quantity);
	}

	/**
	 * Removes a product entirely from the shopping cart.
	 * 
	 * @param product
	 */
	public void removeProduct(Product product) {
		cartRows.remove(product);
	}

	/**
	 * Get all the products and their quantities currently added to the cart.
	 * 
	 * @return
	 */
	public Collection<CartRow> getRows() {
		return cartRows.values();
	}

	/**
	 * The cart content's sum excluding shipping costs.
	 * 
	 * @return
	 */
	public BigDecimal getRowsTotal() {
		return getCartCalculator().getRowsTotal(cartRows.values());
	}

	/**
	 * Get the shipping costs for the products in the cart.
	 * 
	 * @return
	 */
	public BigDecimal getShippingCosts() {
		return getShippingCostCalculator().getShippingCosts(getRowsTotal());
	}

	/**
	 * The cart content's sum including shipping costs.
	 * 
	 * @return
	 */
	public BigDecimal getTotal() {
		return getRowsTotal().add(getShippingCosts());
	}

	private ShippingCostCalculator getShippingCostCalculator() {
		if (shippingCostCalculator == null) {
			shippingCostCalculator = new ShippingCostCalculator();
		}

		return shippingCostCalculator;
	}

	private CartCalculator getCartCalculator() {
		if (cartCalculator == null) {
			cartCalculator = new CartCalculator();
		}

		return cartCalculator;
	}

}
