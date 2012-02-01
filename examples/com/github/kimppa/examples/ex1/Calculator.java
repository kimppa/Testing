package com.github.kimppa.examples.ex1;

public class Calculator {

	/**
	 * Divides x with y and returns the absolute value of the result of the
	 * division.
	 * 
	 * @param x
	 * @param y
	 * @return Absolute value of x/y
	 */
	public float absDiv(int x, int y) {
		if (y == 0) {
			throw new IllegalArgumentException(
					"Y cannot be zero. Division by zero is not allowed");
		}

		return Math.abs(((float) x) / y);
	}
}
