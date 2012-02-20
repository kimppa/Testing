package com.github.kivelae.genome.feature;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;
import org.junit.Assert;

import com.github.kivelae.genome.FeatureContext;
import com.github.kivelae.genome.Genome;
import com.github.kivelae.genome.GenomeVirusMatcher;
import com.github.kivelae.genome.ScoreCalculator;
import com.github.kivelae.genome.Sequence;
import com.github.kivelae.genome.Virus;

public class GenomeSequencerSteps {
	
	private static final String COL_SEQUENCE = "sequence";
	private static final String COL_SCORE = "score";
	private static final String COL_OCCURRENCES = "occurrences";
	private static final String COL_VIRUS = "virus";
	
	private static final String VIRUS_MATCH_COUNT = "virusMatchCount";

	@Given("I have a genome $genomeSequence")
	public void givenIHaveAGenome(String genomeSequence) {
		Genome genome = new Genome(genomeSequence);
		FeatureContext.add(genome);
	}
	
	@Given("virus sequences are: $viruses")
	public void givenVirusSequences(ExamplesTable viruses) {
		for (Parameters row : viruses.getRowsAsParameters()) {
			String virusSequence = row.valueAs(COL_SEQUENCE, String.class);
			Virus virus = new Virus(virusSequence);
			FeatureContext.add(virus);
		}
	}
	
	@Given("I have sequences: $sequences")
	public void givenIHaveSequences(ExamplesTable sequences) {
		Map<Virus, Integer> virusMatchCounts = new HashMap<Virus, Integer>();
		for (Parameters row : sequences.getRowsAsParameters()) {
			String virusSequence = row.valueAs(COL_SEQUENCE, String.class);
			Virus virus = new Virus(virusSequence);
			Integer count = row.valueAs(COL_OCCURRENCES, Integer.class);
			virusMatchCounts.put(virus, count);
		}
		FeatureContext.add(VIRUS_MATCH_COUNT, virusMatchCounts);
	}
	
	@When("I run score calculation from context")
	public void WhenIRunScoreCalculationFromContext() {
		Map<Sequence, Integer> virusMatchCounts = FeatureContext.get(VIRUS_MATCH_COUNT);
		runScoreCalculation(virusMatchCounts);
	}
	
	private void runScoreCalculation(Map<Sequence, Integer> virusMatchCounts) {
		ScoreCalculator scoreCalculator = new ScoreCalculator(virusMatchCounts);
		FeatureContext.add(scoreCalculator);
	}

	@When("I run score calculation")
	public void WhenIRunScoreCalculation() {
		GenomeVirusMatcher genomeVirusMatcher = FeatureContext.getFirst(GenomeVirusMatcher.class);
		Map<Sequence, Integer> virusMatchCounts = genomeVirusMatcher.getVirusMatches();
		runScoreCalculation(virusMatchCounts);
	}
	
	@When("I run sequencer")
	public void whenIRunSequencer() {
		GenomeVirusMatcher genomeVirusMatcher = new GenomeVirusMatcher(FeatureContext.getFirst(Genome.class));
		Virus[] viruses = FeatureContext.get(Virus.class).toArray(new Virus[1]);
		genomeVirusMatcher.addVirusCandidates(viruses );
		FeatureContext.add(genomeVirusMatcher);
	}
	
	@Then("scores per viruses are: $scoreTable")
	public void thenScoresPerVirusesAre(ExamplesTable scoreTable) {
		ScoreCalculator scoreCalculator = FeatureContext.getFirst(ScoreCalculator.class);
		for (Parameters row : scoreTable.getRowsAsParameters()) {
			String virusSequence = row.valueAs(COL_VIRUS, String.class);
			Virus virus = new Virus(virusSequence);
			int expectedScore = row.valueAs(COL_SCORE, Integer.class);

			int actualScore = scoreCalculator.getScoreForSequence(virus);
			Assert.assertEquals(expectedScore, actualScore);
		}
	}
	
	@Then("total score is $totalScore")
	public void theTotalScoreIs(int totalScore) {
		ScoreCalculator scoreCalculator = FeatureContext.getFirst(ScoreCalculator.class);
		int actualTotalScore = scoreCalculator.getTotalScore();
		Assert.assertEquals(totalScore, actualTotalScore);
	}
	
	@Then("found viruses are: $foundViruses")
	public void thenFoundVirusesAre(ExamplesTable foundViruses) {
		GenomeVirusMatcher genomeVirusMatcher = FeatureContext.getFirst(GenomeVirusMatcher.class);
		Map<Sequence, Integer> virusMatches = genomeVirusMatcher.getVirusMatches();
		for (Parameters row : foundViruses.getRowsAsParameters()) {
			String virusSequence = row.valueAs(COL_VIRUS, String.class);
			Virus virus = new Virus(virusSequence);
			
			assertTrue(virusMatches.containsKey(virus));
			
			int matchCount = virusMatches.get(virus);
			int expectedMatchCount = row.valueAs(COL_OCCURRENCES, Integer.class);
			
			assertEquals(expectedMatchCount, matchCount);
		}
	}
}
