package com.github.kimppa.exercises.tdd.genome.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenomeVirusFinder {

	/**
	 * Tries to find the given viruses in the given sequence.
	 * 
	 * @param sequence
	 * @param viruses
	 * @return Returns a map between the viruses found and the number of time
	 *         they occurred.
	 */
	public Map<String, Integer> findViruses(String sequence,
			List<String> viruses) {

		Map<String, Integer> result = new HashMap<String, Integer>();

		for (String virus : viruses) {
			int matchesFound = findVirus(sequence, virus);
			if (matchesFound > 0) {
				result.put(virus, matchesFound);
			}
		}

		return result;
	}

	private int findVirus(String sequence, String virus) {
		int matchesFound = 0;
		int index = sequence.indexOf(virus);
		while (index >= 0) {
			matchesFound++;
			sequence = sequence.substring(index + 1);
			index = sequence.indexOf(virus);
		}

		return matchesFound;
	}

}
