package com.github.kimppa.exercises.ex1;

public class FunnyStrings {
	
	/**
	 * This method converts every third character in the given string to upper case.
	 * 
	 * @param boringString
	 * @return Returns a string where every third character is upper case.
	 */
	public String applyFunny(String boringString) {
		if (boringString == null) {
			return null;
		}
		
		if(boringString.length() < 3) {
			return boringString;
		}

		StringBuffer funnyString = new StringBuffer();
		for (int i = 0; i < boringString.length(); i++) {
			String c = boringString.substring(i, i+1);
			if(i > 0 && i%3 == 2) {
				funnyString.append(c.toUpperCase());
			} else {
				funnyString.append(c);
			}
		}

		return funnyString.toString();
	}

}
