package com.github.kimppa.exercises.ex2;

public class BBHtmlConverter {

	/**
	 * Replaces '<' and '>' characters with their HTML entity equivalents.
	 * @param message
	 * @return
	 */
	public String replaceTagsWithHtmlEntities(String message) {
		message = message.replace("<", "&lt;");
		message = message.replace(">", "&gt;");
		return message;
	}
}
