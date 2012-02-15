package com.github.kimppa.exercises.tdd.genome;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.kimppa.exercises.tdd.genome.util.GenomeVirusFinder;
import com.github.kimppa.exercises.tdd.genome.util.GenomeVirusScorer;

public class GenomeVirusSequencer {

	private List<String> virusSequences = new ArrayList<String>();

	private GenomeVirusFinder genomeVirusFinder = new GenomeVirusFinder();

	private GenomeVirusScorer genomeVirusScorer = new GenomeVirusScorer();

	public GenomeVirusSequencer() {
		virusSequences.add("AAA");
		virusSequences.add("ABC");
		virusSequences.add("ABB");
		virusSequences.add("ABBA");
		virusSequences.add("ACDC");
		virusSequences.add("CEDFF");
		virusSequences.add("AFEED");
	}

	/**
	 * Scores the sequence for viruses
	 * 
	 * @param sequence
	 * @return
	 */
	public int getVirusScore(String sequence) {
		Map<String, Integer> virusesFound = genomeVirusFinder.findViruses(
				sequence, virusSequences);
		return genomeVirusScorer.getTotalScore(virusesFound);
	}
}
