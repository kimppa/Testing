package com.github.kimppa.exercises.tdd.genome;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.kimppa.exercises.tdd.genome.util.GenomeVirusFinder;
import com.github.kimppa.exercises.tdd.genome.util.GenomeVirusScorer;

public class GenomeVirusSequencerTests {
	
	@Mock
	private GenomeVirusFinder genomeVirusFinder;
	
	@Mock
	private GenomeVirusScorer genomeVirusScorer;
	
	@InjectMocks
	private GenomeVirusSequencer sequencer;
	
	@Before
	public void setUp() {
		sequencer = new GenomeVirusSequencer();
		MockitoAnnotations.initMocks(this);
	}	
	
	@Test
	public void getVirusScore_virusFinderAndScorerCalledCorrectlyAndScoreReturned() {
		String sequence = "ABBAACDEEEFA";
		
		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put("ABC", 10);
		
		ArgumentCaptor<String> sequenceCapture = ArgumentCaptor.forClass(String.class);
		Mockito.when(genomeVirusFinder.findViruses(sequenceCapture.capture(), Mockito.anyListOf(String.class))).thenReturn(result);
		Mockito.when(genomeVirusScorer.getTotalScore(result)).thenReturn(10);
		InOrder inOrder = Mockito.inOrder(genomeVirusFinder, genomeVirusScorer);
		
		int score = sequencer.getVirusScore(sequence);
		
		Assert.assertEquals(10, score);
		Assert.assertEquals(sequence, sequenceCapture.getAllValues().get(0));
		
		inOrder.verify(genomeVirusFinder, Mockito.times(1)).findViruses(sequenceCapture.capture(), Mockito.anyListOf(String.class));
		inOrder.verify(genomeVirusScorer, Mockito.times(1)).getTotalScore(result);
	}

}
