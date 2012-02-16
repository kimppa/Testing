package com.github.kivelae.genome.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.kivelae.genome.Genome;
import com.github.kivelae.genome.SequenceFinder;
import com.github.kivelae.genome.Virus;

public class GenomeTest {

	@Mock Virus virus1;
	@Mock SequenceFinder sequenceFinder;
	@InjectMocks Genome genome;
	
	String genomeSequence = "AATTCCGGATT";
	String virusSequence1 = "ATT";
	int virusMatchCount = 2;

	
	
	@Before
	public void setUp() {
		genome = new Genome(genomeSequence);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void GetGenomeShouldReturnGivenGenome() {
		assertEquals(genomeSequence, genome.getGenome());
	}
	
	@Test
	public void countSequenceMatches_ShouldReturnCountFromSequenceFinder() {
		Mockito.when(sequenceFinder.countMatches(virus1)).thenReturn(virusMatchCount);
		
		int virusCount = genome.countSequenceMatches(virus1);
		
		assertEquals(virusMatchCount, virusCount);
		
		Mockito.verify(sequenceFinder, Mockito.times(1)).countMatches(virus1);
	}
}
