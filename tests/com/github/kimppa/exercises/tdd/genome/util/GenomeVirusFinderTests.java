package com.github.kimppa.exercises.tdd.genome.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class GenomeVirusFinderTests {
	
	@Test
	public void findViruses_virusIsAAASequnceIsAAA_oneMatchFound() {
		List<String> virusSequences = new ArrayList<String>();
		virusSequences.add("AAA");
		
		GenomeVirusFinder finder = new GenomeVirusFinder();
		Map<String, Integer> result = finder.findViruses("AAA", virusSequences);
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(result.containsKey("AAA"));
		Assert.assertEquals(1, result.get("AAA").intValue());
	}
	
	@Test
	public void findViruses_virusInTheStartOfTheSequence_oneMatchFound() {
		List<String> virusSequences = new ArrayList<String>();
		virusSequences.add("ABC");
		
		GenomeVirusFinder finder = new GenomeVirusFinder();
		Map<String, Integer> result = finder.findViruses("ABCD", virusSequences);
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(result.containsKey("ABC"));
		Assert.assertEquals(1, result.get("ABC").intValue());
	}
	
	@Test
	public void findViruses_virusInTheMiddleOfTheSequence_oneMatchFound() {
		List<String> virusSequences = new ArrayList<String>();
		virusSequences.add("ABC");
		
		GenomeVirusFinder finder = new GenomeVirusFinder();
		Map<String, Integer> result = finder.findViruses("EFABCD", virusSequences);
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(result.containsKey("ABC"));
		Assert.assertEquals(1, result.get("ABC").intValue());
	}
	
	@Test
	public void findViruses_virusInTheEndOfTheSequence_oneMatchFound() {
		List<String> virusSequences = new ArrayList<String>();
		virusSequences.add("ABC");
		
		GenomeVirusFinder finder = new GenomeVirusFinder();
		Map<String, Integer> result = finder.findViruses("EDFABC", virusSequences);
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(result.containsKey("ABC"));
		Assert.assertEquals(1, result.get("ABC").intValue());
	}
	
	@Test
	public void findViruses_virusNotFoundInTheSequence_zeroMatchFound() {
		List<String> virusSequences = new ArrayList<String>();
		virusSequences.add("ABC");
		
		GenomeVirusFinder finder = new GenomeVirusFinder();
		Map<String, Integer> result = finder.findViruses("EDFEDF", virusSequences);
		Assert.assertEquals(0, result.size());
	}
	
	@Test
	public void findViruses_virusIsShortAndMatchesLastCharacter_threeMatchFound() {
		List<String> virusSequences = new ArrayList<String>();
		virusSequences.add("B");
		
		GenomeVirusFinder finder = new GenomeVirusFinder();
		Map<String, Integer> result = finder.findViruses("BBB", virusSequences);
		Assert.assertEquals(1, result.size());
		Assert.assertTrue(result.containsKey("B"));
		Assert.assertEquals(3, result.get("B").intValue());
	}
	
	@Test
	public void findViruses_multipleVirusesInSequence_allFound() {
		List<String> virusSequences = new ArrayList<String>();
		virusSequences.add("AAA");
		virusSequences.add("AAB");
		
		GenomeVirusFinder finder = new GenomeVirusFinder();
		Map<String, Integer> result = finder.findViruses("BAAAAB", virusSequences);
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(result.containsKey("AAA"));
		Assert.assertTrue(result.containsKey("AAB"));
		Assert.assertEquals(2, result.get("AAA").intValue());
		Assert.assertEquals(1, result.get("AAB").intValue());
	}

}
