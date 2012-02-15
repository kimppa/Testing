package com.github.kimppa.exercises.tdd.genome.util;

import java.util.Map;

public class GenomeVirusScorer {

	/**
	 * Calculates the total score of all the viruses found.
	 * 
	 * @param virusMatches
	 * @return
	 */
	public int getTotalScore(Map<String, Integer> virusMatches) {
		int totalScore = 0;
		for (String virus : virusMatches.keySet()) {
			totalScore += getScore(virus, virusMatches.get(virus));
		}

		return totalScore;
	}

	/**
	 * Calculates the score for the virus and the number of matches of it.
	 * 
	 * @param virus
	 * @param matches
	 * @return
	 */
	public int getScore(String virus, int matches) {
		if (matches < 3) {
			return 0;
		}

		int baseScore = 0;
		if (virus.length() == 3) {
			baseScore = 10;
		} else if (virus.length() == 4) {
			baseScore = 20;
		} else if (virus.length() == 5) {
			baseScore = 40;
		}

		return baseScore * (matches - 2);
	}

}
