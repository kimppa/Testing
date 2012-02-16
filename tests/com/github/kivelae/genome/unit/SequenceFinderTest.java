package com.github.kivelae.genome.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.kivelae.genome.Sequence;
import com.github.kivelae.genome.SequenceFinder;

public class SequenceFinderTest {

	@Mock Sequence baseSequence;
	@Mock Sequence subSequence;
	
	SequenceFinder sequenceFinder;
	
	final String baseSequenceStr = "AATTTTTGGAATTT";

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		sequenceFinder = new SequenceFinder(baseSequence);
		
		Mockito.when(baseSequence.getSequence()).thenReturn(baseSequenceStr);
	}
	
	@Test
	public void countMatches_ReturnsCorrectCount() {
		final String subSequenceStr = "AAT";
		final int expectedMatchCount = 2;
		
		Mockito.when(subSequence.getSequence()).thenReturn(subSequenceStr);
		
		int actualMatchCount = sequenceFinder.countMatches(subSequence);
		
		assertEquals(expectedMatchCount, actualMatchCount);
	}
	
	@Test
	public void countMatches_ReturnsCorrectCountWithOverlappingMatches() {
		final String subSequenceStr = "TTT";
		final int expectedMatchCount = 4;
		
		Mockito.when(subSequence.getSequence()).thenReturn(subSequenceStr);
		
		int actualMatchCount = sequenceFinder.countMatches(subSequence);
		
		assertEquals(expectedMatchCount, actualMatchCount);
	}
	
	
}
