package com.github.kimppa.exercises.tdd.shopping;

import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class CartTests {

	private Cart cart;

	@Before
	public void setUp() {
		cart = new Cart();
	}

	@Test
	public void addProduct_firstProductAdded_quantityIs1() {
		Product product = new Product();
		product.setId(1);

		Assert.assertEquals(0, cart.getRows().size());
		cart.addProduct(product);
		Assert.assertEquals(1, cart.getRows().size());

		Assert.assertEquals(1, cart.getRows().iterator().next().getQuantity());
	}

	@Test
	public void addProduct_sameProductAddedTwice_quantityIs2() {
		Product product = new Product();
		product.setId(1);

		Assert.assertEquals(0, cart.getRows().size());
		cart.addProduct(product);
		cart.addProduct(product);
		Assert.assertEquals(1, cart.getRows().size());

		Assert.assertEquals(2, cart.getRows().iterator().next().getQuantity());
	}

	@Test
	public void addProduct_productAddedWithQuantity3_quantityIs3() {
		Product product = new Product();
		product.setId(1);

		Assert.assertEquals(0, cart.getRows().size());
		cart.addProduct(product, 3);
		Assert.assertEquals(1, cart.getRows().size());

		Assert.assertEquals(3, cart.getRows().iterator().next().getQuantity());
	}

	@Test
	public void addProduct_sameProductAddedTwiceWithQuantity3_quantityIs6() {
		Product product = new Product();
		product.setId(1);

		Assert.assertEquals(0, cart.getRows().size());
		cart.addProduct(product, 3);
		cart.addProduct(product, 3);
		Assert.assertEquals(1, cart.getRows().size());

		Assert.assertEquals(6, cart.getRows().iterator().next().getQuantity());
	}

	@Test
	public void addProduct_twoDifferentProductsAdded_twoRowsCreated() {
		Product product1 = new Product();
		product1.setId(1);

		Product product2 = new Product();
		product2.setId(2);

		Assert.assertEquals(0, cart.getRows().size());
		cart.addProduct(product1, 3);
		cart.addProduct(product2, 2);
		Assert.assertEquals(2, cart.getRows().size());

		Iterator<CartRow> it = cart.getRows().iterator();
		while (it.hasNext()) {
			CartRow row = it.next();
			if (row.getProduct() == product1) {
				Assert.assertEquals(3, row.getQuantity());
			} else {
				Assert.assertEquals(2, row.getQuantity());
			}
		}
	}
	
	@Test
	public void setProductQuantity_productQuantitySetTo5_quantityUpdated() {
		Product product = new Product();
		product.setId(1);

		Assert.assertEquals(0, cart.getRows().size());
		cart.addProduct(product);
		cart.setProductQuantity(product, 5);

		Assert.assertEquals(5, cart.getRows().iterator().next().getQuantity());
	}
	
	@Test
	public void removeProduct_removeAProduct_noLongerInRowList() {
		Product product1 = new Product();
		product1.setId(1);

		Product product2 = new Product();
		product2.setId(2);

		Assert.assertEquals(0, cart.getRows().size());
		cart.addProduct(product1);
		cart.addProduct(product2);
		cart.removeProduct(product1);
		Assert.assertEquals(1, cart.getRows().size());
		Assert.assertEquals(product2, cart.getRows().iterator().next().getProduct());
	}

}
