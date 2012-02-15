package com.github.kimppa.exercises.tdd.genome.util;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class GenomeVirusScorerTests {
	
	@Test
	public void getScore_virusLenghtUnder3_returns0() {
		int result = new GenomeVirusScorer().getScore("A", 5);
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void getScore_virusLenght3matcheCount2_returns0() {
		int result = new GenomeVirusScorer().getScore("ABC", 2);
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void getScore_virusLenght3matcheCount3_returns10() {
		int result = new GenomeVirusScorer().getScore("ABC", 3);
		Assert.assertEquals(10, result);
	}
	
	@Test
	public void getScore_virusLenght3matcheCount5_returns30() {
		int result = new GenomeVirusScorer().getScore("ABC", 5);
		Assert.assertEquals(30, result);
	}
	
	@Test
	public void getScore_virusLenght4matcheCount2_returns0() {
		int result = new GenomeVirusScorer().getScore("ABCD", 2);
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void getScore_virusLenght4matcheCount3_returns20() {
		int result = new GenomeVirusScorer().getScore("ABCD", 3);
		Assert.assertEquals(20, result);
	}
	
	@Test
	public void getScore_virusLenght4matcheCount5_returns60() {
		int result = new GenomeVirusScorer().getScore("ABCD", 5);
		Assert.assertEquals(60, result);
	}
	
	@Test
	public void getScore_virusLenght5matcheCount2_returns0() {
		int result = new GenomeVirusScorer().getScore("ABCDE", 2);
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void getScore_virusLenght5matcheCount3_returns40() {
		int result = new GenomeVirusScorer().getScore("ABCDE", 3);
		Assert.assertEquals(40, result);
	}
	
	@Test
	public void getScore_virusLenght5matcheCount5_returns120() {
		int result = new GenomeVirusScorer().getScore("ABCDE", 5);
		Assert.assertEquals(120, result);
	}
	
	@Test
	public void getTotalScore_noVirusesFound_returns0() {
		Map<String, Integer> virusMatches = new HashMap<String, Integer>();
		int result = new GenomeVirusScorer().getTotalScore(virusMatches);
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void getTotalScore_threeShortVirusFound_returns10() {
		Map<String, Integer> virusMatches = new HashMap<String, Integer>();
		virusMatches.put("ABC", 3);
		
		int result = new GenomeVirusScorer().getTotalScore(virusMatches);
		Assert.assertEquals(10, result);
	}
	
	@Test
	public void getTotalScore_multipleVirusesFound_scoreCaluclatedCorrectly() {
		Map<String, Integer> virusMatches = new HashMap<String, Integer>();
		virusMatches.put("ABC", 3);
		virusMatches.put("DEF", 6);
		virusMatches.put("AABB", 4);
		virusMatches.put("ACDCF", 4);
		virusMatches.put("ABBA", 2);
		
		int result = new GenomeVirusScorer().getTotalScore(virusMatches);
		Assert.assertEquals(170, result);
	}

}
