package com.github.kivelae.genome;

import java.util.HashMap;
import java.util.Map;

public class GenomeVirusMatcher {
	
	private Genome genome;
	private Map<Sequence, Integer> virusMatches;

	public GenomeVirusMatcher(Genome genome) {
		this.genome = genome;
		virusMatches = new HashMap<Sequence, Integer>();
	}
	
	public void addVirusCandidates(Virus... viruses) {
		for (Virus virus : viruses) {
			int matchCount = match(virus);
			this.virusMatches.put(virus, matchCount);
		}
	}
	
	private int match(Virus virus) {
		return genome.countSequenceMatches(virus);
	}
	
	public Map<Sequence, Integer> getVirusMatches() {
		return virusMatches;
	}
}
