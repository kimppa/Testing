package com.github.kivelae.genome;

public class Genome implements Sequence {
	
	private String genome;
	private SequenceFinder sequenceFinder;
	
	public Genome(String genome) {
		this.genome = genome;
	}

	public String getGenome() {
		return this.genome;
	}

	private SequenceFinder getSequenceFinder() {
		if (sequenceFinder == null) {
			sequenceFinder = new SequenceFinder(this);
		}
		return sequenceFinder;
	}
	
	@Override
	public String getSequence() {
		return genome;
	}

	public int countSequenceMatches(Sequence sequence) {
		return getSequenceFinder().countMatches(sequence);
	}
}
