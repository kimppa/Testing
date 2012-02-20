package com.github.kimppa.examples.ex1;

import junit.framework.Assert;

import org.junit.Test;


public class CalculatorTests {
	
	@Test(expected=IllegalArgumentException.class)
	public void absDiv_yIsZero_exceptionThrown() {
		new Calculator().absDiv(0, 0);
	}
	
	@Test
	public void absDiv_xIsZero_returnsZero() {
		float result = new Calculator().absDiv(0, 3);
		Assert.assertEquals(0f, result);
	}
	
	@Test
	public void absDiv_xAndYCanBeDividedEvenly_returnsCorrectValue() {
		float result = new Calculator().absDiv(12, 3);
		Assert.assertEquals(4f, result);
	}
	
	@Test
	public void absDiv_xAndYCannotBeDividedEvenly_returnsCorrectValue() {
		float result = new Calculator().absDiv(15, 6);
		Assert.assertEquals(2.5f, result);
	}
	
	@Test
	public void absDiv_xIsNegative_returnsPositiveValue() {
		float result = new Calculator().absDiv(-15, 6);
		Assert.assertEquals(2.5f, result);
	}
	
	@Test
	public void absDiv_yIsNegative_returnsPositiveValue() {
		float result = new Calculator().absDiv(15, -6);
		Assert.assertEquals(2.5f, result);
	}
	
	@Test
	public void absDiv_xAndYAreNegative_returnsPositiveValue() {
		float result = new Calculator().absDiv(-15, -6);
		Assert.assertEquals(2.5f, result);
	}

}
