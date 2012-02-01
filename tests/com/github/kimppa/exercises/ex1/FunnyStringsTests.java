package com.github.kimppa.exercises.ex1;

import junit.framework.Assert;

import org.junit.Test;

public class FunnyStringsTests {

	@Test
	public void applyFunny_nullAsInput_nullReturned()  {
		String result = new FunnyStrings().applyFunny(null);
		Assert.assertNull(result);
	}
	
	@Test
	public void applyFunny_inputLessThanThreeCharacters_sameStringReturned()  {
		String result = new FunnyStrings().applyFunny("ab");
		Assert.assertEquals("ab", result);
	}
	
	@Test
	public void applyFunny_inputExactlyThreeCharacters_lastLetterIsUpperCase()  {
		String result = new FunnyStrings().applyFunny("abc");
		Assert.assertEquals("abC", result);
	}
	
	@Test
	public void applyFunny_inputExactlyFourCharacters_lastLetterIsUpperCase()  {
		String result = new FunnyStrings().applyFunny("abce");
		Assert.assertEquals("abCe", result);
	}
	
	@Test
	public void applyFunny_inputLongerThan3CharsLengthEquallyDividableBy3_correctlyUpperCased()  {
		String result = new FunnyStrings().applyFunny("abcdef");
		Assert.assertEquals("abCdeF", result);
	}
	
	@Test
	public void applyFunny_inputLongerThan3CharsLengthNotEquallyDividableBy3_correctlyUpperCased()  {
		String result = new FunnyStrings().applyFunny("abcdefg");
		Assert.assertEquals("abCdeFg", result);
	}
	
	@Test
	public void applyFunny_every3rdCharacterIsAlreadyUpperCase_sameStringReturned()  {
		String result = new FunnyStrings().applyFunny("abCdeF");
		Assert.assertEquals("abCdeF", result);
	}
	
	@Test
	public void applyFunny_3rdCharacterIsNotALetter_sameStringReturned()  {
		String result = new FunnyStrings().applyFunny("ab#");
		Assert.assertEquals("ab#", result);
	}
	
	@Test
	public void applyFunny_3rdCharacterIsAnAccentedLetter_correctlyUpperCased()  {
		String result = new FunnyStrings().applyFunny("abå");
		Assert.assertEquals("abÅ", result);
	}
}
