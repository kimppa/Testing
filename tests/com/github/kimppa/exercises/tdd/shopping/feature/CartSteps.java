package com.github.kimppa.exercises.tdd.shopping.feature;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertEquals;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;

import com.github.kimppa.exercises.tdd.shopping.Cart;
import com.github.kimppa.exercises.tdd.shopping.CartRow;
import com.github.kimppa.exercises.tdd.shopping.Product;
import com.github.kivelae.genome.FeatureContext;

public class CartSteps {

	@Given("I start debugging")
	public void givenIStartDebugging() {
		int i = 0;
	}
	
	@Given("products exist: $products")
	public void givenProductsExist(ExamplesTable products) {
		for (Parameters row : products.getRowsAsParameters()) {
			int id = row.valueAs("id", Integer.class);
			String name = row.valueAs("name", String.class);
			BigDecimal unitPrice = row.valueAs("unit price", BigDecimal.class);
			
			Product product = new Product();
			product.setId(id);
			product.setName(name);
			product.setUnitPrice(unitPrice);
			
			FeatureContext.add(product);
		}
	}
	
	@Given("I have an empty cart")
	public void givenIHaveAnEmptyCart() {
		Cart cart = new Cart();
		FeatureContext.add(cart);
	}
	
	@When("I add <quantity> pc of product <product id> to the cart")
	public void whenIAddProductsToCartExamples(@Named("quantity") int quantity, @Named("product id") int productId) {
		addQuantityOfProductToCart(quantity, productId);
	}
	
	@When("I add $quantity pc of product $productId to the cart")
	public void whenIAddProductsToCart(int quantity, int productId) {
		addQuantityOfProductToCart(quantity, productId);
	}

	private void addQuantityOfProductToCart(int quantity, int productId) {
		Cart cart = FeatureContext.getFirst(Cart.class);
		Product product = findProductFromContext(productId);
		
		cart.addProduct(product, quantity);
	}

	@When("I remove product $productId from the cart")
	public void whenIRemoveProductsFromCart(int productId) {
		Cart cart = FeatureContext.getFirst(Cart.class);
		Product product = findProductFromContext(productId);
		
		cart.removeProduct(product);
	}
	
	@Then("the cart contains row for product <product id> with quantity of <quantity> and discounted amount <discounted amount>")
	public void thenCartContainsRowProductWithQuantityAndDiscountedAmount(
			@Named("product id") int productId,
			@Named("quantity") int quantity,
			@Named("discounted amount") BigDecimal discountedAmount) {
		Cart cart = FeatureContext.getFirst(Cart.class);
		CartRow cartRow = findRowFromCart(productId, cart);
		
		assertEquals(quantity, cartRow.getQuantity());
		assertEquals(discountedAmount, cartRow.getRowTotal());
	}
	
	
	@Then("the cart contains rows: $expectedCartRows")
	public void thenCartContainsRows(ExamplesTable expectedCartRows) {
		Cart cart = FeatureContext.getFirst(Cart.class);
		assertEquals(expectedCartRows.getRowCount(), cart.getRows().size());
		
		for (Parameters row : expectedCartRows.getRowsAsParameters()) {
			int productId = row.valueAs("product id", Integer.class);
			CartRow cartRow = findRowFromCart(productId, cart);
			
			int expectedQuantity = row.valueAs("quantity", Integer.class);
			assertEquals(expectedQuantity, cartRow.getQuantity());
			
			if (row.values().containsKey("discounted price")) {
				BigDecimal totalPrice = row.valueAs("discounted price", BigDecimal.class);
				assertEquals(totalPrice, cartRow.getRowTotal());
			}
		}
	}

	@Then("the cart is empty")
	public void thenCartIsEmpty() {
		Cart cart = FeatureContext.getFirst(Cart.class);
		assertEquals(0, cart.getRows().size());
	}
	
	@Then("shipping cost is <shipping cost>")
	public void thenShippingCostIs(@Named("shipping cost") BigDecimal expectedShippingCost) {
		Cart cart = FeatureContext.getFirst(Cart.class);
		assertEquals(expectedShippingCost, cart.getShippingCosts());
	}
	
	private CartRow findRowFromCart(int productId, Cart cart) {
		Collection<CartRow> cartRows = cart.getRows();
		
		for (CartRow cartRow : cartRows) {
			if (cartRow.getProduct().getId() == productId)
				return cartRow;
		}
		throw new RuntimeException("product " + productId + " not found from cart");
	}
	
	private Product findProductFromContext(int productId) {
		List<Product> products = FeatureContext.get(Product.class);
		for (Product product : products) {
			if (product.getId() == productId)
				return product;
		}
		throw new RuntimeException("product "+productId+" not found from context");
	}
}
