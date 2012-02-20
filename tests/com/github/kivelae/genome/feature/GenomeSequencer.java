package com.github.kivelae.genome.feature;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.UsingPaths;
import org.junit.runner.RunWith;

import com.github.kivelae.genome.FeatureContext;

import semmenla.jbehave.junit.JBehaveTestRunner;
import semmenla.jbehave.junit.Steps;

@RunWith(JBehaveTestRunner.class)
@UsingPaths(searchIn="com/github/kivelae/genome/feature", includes="GenomeSequencer.story")
public class GenomeSequencer {
	@Steps
	public Object getSteps() {
		return new Object[]{ new GenomeSequencerSteps() };
	}
	
	@BeforeScenario
	public void beforeScenario() {
		FeatureContext.initialize();
	}
}
