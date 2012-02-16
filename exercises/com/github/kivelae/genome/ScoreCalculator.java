package com.github.kivelae.genome;

import java.util.Map;

public class ScoreCalculator {

	private Map<Sequence, Integer> counts;

	public ScoreCalculator(Map<Sequence, Integer> counts) {
		this.counts = counts;
	}
	
	public int getTotalScore() {
		int score = 0;
		for (Sequence seq : counts.keySet()) {
			score += countScore(seq);
		}
		return score;
	}
	
	public int getScoreForSequence(Sequence sequence) {
		if (counts.containsKey(sequence)) {
			return countScore(sequence);
		}
		return 0;
	}

	private int countScore(Sequence sequence) {
		int baseScore = getBaseScore(sequence);
		
		int score = baseScore * getScoreMultiplier(counts.get(sequence));
		
		return score;
	}

	private int getBaseScore(Sequence sequence) {
		int length = sequence.getSequence().length();
		
		if (length < 4) return 10;
		if (length < 7) return 20;
		else return 40;
	}

	private int getScoreMultiplier(Integer count) {
		if (count < 3) return 0;
		return count - 2;
	}
}
