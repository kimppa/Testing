package com.github.kivelae.genome;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SequenceFinder {

	private Sequence baseSequence;

	public SequenceFinder(Sequence baseSequence) {
		this.baseSequence = baseSequence;
	}
	
	public int countMatches(Sequence subSequence) {
		String seq = baseSequence.getSequence();
		String sub = subSequence.getSequence();
		
        Pattern pattern = Pattern.compile("(?="+sub+")");
        Matcher  matcher = pattern.matcher(seq);

        int count = 0;
        while (matcher.find())
            count++;
		
		return count;
	}
	
	

}
