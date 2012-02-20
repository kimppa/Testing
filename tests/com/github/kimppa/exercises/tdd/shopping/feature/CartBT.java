package com.github.kimppa.exercises.tdd.shopping.feature;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.UsingPaths;
import org.junit.runner.RunWith;

import semmenla.jbehave.junit.BeforeExample;
import semmenla.jbehave.junit.JBehaveTestRunner;
import semmenla.jbehave.junit.Steps;

import com.github.kivelae.genome.FeatureContext;


@RunWith(JBehaveTestRunner.class)
@UsingPaths(searchIn="com/github/kimppa/exercises/tdd/shopping/feature", includes={"Cart.story" })
public class CartBT {
	@Steps
	public Object getSteps() {
		return new Object[]{ new CartSteps() };
	}
	
	@BeforeScenario
	@BeforeExample
	public void beforeScenario() {
		FeatureContext.initialize();
	}
}