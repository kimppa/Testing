package com.github.kivelae.genome.unit;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.kivelae.genome.ScoreCalculator;
import com.github.kivelae.genome.Sequence;
import com.github.kivelae.genome.SequenceFinder;

public class ScoreCalculatorTest {

	@Mock Sequence sequence1;
	@Mock Sequence sequence2;
	@Mock Sequence sequence3;
	
	ScoreCalculator scoreCalculator;
	
	final String sequenceStr1 = "AAA";
	final String sequenceStr2 = "TTTGGG";
	final String sequenceStr3 = "TTTGGGCCC";
	
	final int expectedScoreSequence1 = 20;
	final int expectedScoreSequence2 = 40;
	final int expectedScoreSequence3 = 80;
	final int expectedTotalScore = expectedScoreSequence1 + expectedScoreSequence2 + expectedScoreSequence3;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		setSequenceReturnValues();
		
		Map<Sequence, Integer> counts = createSequenceCounts();
		scoreCalculator = new ScoreCalculator(counts);
	}


	private void setSequenceReturnValues() {
		Mockito.when(sequence1.getSequence()).thenReturn(sequenceStr1);
		Mockito.when(sequence2.getSequence()).thenReturn(sequenceStr2);
		Mockito.when(sequence3.getSequence()).thenReturn(sequenceStr3);
	}

	
	private Map<Sequence, Integer> createSequenceCounts() {
		Map<Sequence, Integer> counts = new HashMap<Sequence, Integer>();
		counts.put(sequence1, 4);
		counts.put(sequence2, 4);
		counts.put(sequence3, 4);
		return counts;
	}


	@Test
	public void getTotalScore_ReturnsCorrectScore() {
		int totalScore = scoreCalculator.getTotalScore();
		assertEquals(expectedTotalScore, totalScore);
	}

	@Test
	public void getScoreForSequence_ReturnCorrectScore() {
		int score = scoreCalculator.getScoreForSequence(sequence1);
		assertEquals(expectedScoreSequence1, score);
		
		score = scoreCalculator.getScoreForSequence(sequence2);
		assertEquals(expectedScoreSequence2, score);
		
		score = scoreCalculator.getScoreForSequence(sequence3);
		assertEquals(expectedScoreSequence3, score);
	}
	
	@Test
	public void getScoreForSequence_ShouldReturnZeroWhenCountIsLessThan3() {
		Map<Sequence, Integer> counts = createSequenceCounts();
		counts.put(sequence1, 2);
		scoreCalculator = new ScoreCalculator(counts);
		
		int score = scoreCalculator.getScoreForSequence(sequence1);
		assertEquals(0, score);
	}

}
