package com.github.kimppa.exercises.tdd.shopping;

import java.math.BigDecimal;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.kimppa.exercises.tdd.shopping.util.CartCalculator;
import com.github.kimppa.exercises.tdd.shopping.util.ShippingCostCalculator;

public class CartTests {

	@Mock
	private ShippingCostCalculator shippingCostCalculator;
	
	@Mock
	private CartCalculator cartCalculator;
	
	@InjectMocks
	private Cart cart;

	@Before
	public void setUp() {
		cart = new Cart();
		MockitoAnnotations.initMocks(this);
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
	
	@Test
	public void getRowsTotal_calculatorIsCalled() {
		Mockito.when(cartCalculator.getRowsTotal(Mockito.anyCollectionOf(CartRow.class))).thenReturn(new BigDecimal("100"));
		BigDecimal result = cart.getRowsTotal();
		Assert.assertEquals(0, new BigDecimal("100").compareTo(result));
		Mockito.verify(cartCalculator).getRowsTotal(Mockito.anyCollectionOf(CartRow.class));
	}
	
	@Test
	public void getShippingCosts_shippingCostCalculatorIsCalled() {
		Mockito.when(cartCalculator.getRowsTotal(Mockito.anyCollectionOf(CartRow.class))).thenReturn(new BigDecimal("100"));
		Mockito.when(shippingCostCalculator.getShippingCosts(Mockito.any(BigDecimal.class))).thenReturn(new BigDecimal("20"));
		BigDecimal result = cart.getShippingCosts();
		Assert.assertEquals(0, new BigDecimal("20").compareTo(result));
		Mockito.verify(shippingCostCalculator).getShippingCosts(Mockito.any(BigDecimal.class));
	}
	
	@Test
	public void getTotal_shippingCosts20RowTotal300_returns320() {
		Mockito.when(cartCalculator.getRowsTotal(Mockito.anyCollectionOf(CartRow.class))).thenReturn(new BigDecimal("300"));
		Mockito.when(shippingCostCalculator.getShippingCosts(Mockito.any(BigDecimal.class))).thenReturn(new BigDecimal("20"));
		BigDecimal result = cart.getTotal();
		Assert.assertEquals(0, new BigDecimal("320").compareTo(result));
	}

}
