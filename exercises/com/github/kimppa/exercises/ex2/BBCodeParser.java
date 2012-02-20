package com.github.kimppa.exercises.ex2;

public class BBCodeParser {

	/**
	 * Parses [b], [i] and [u] bb-codes and replaces them with their HTML tag
	 * equivalents.
	 * 
	 * @param message
	 * @return
	 */
	public String parseBBCodes(String message) {
		message = message.replace("[b]", "<b>");
		message = message.replace("[/b]", "</b>");
		message = message.replace("[u]", "<u>");
		message = message.replace("[/u]", "</u>");
		message = message.replace("[i]", "<i>");
		message = message.replace("[/i]", "</i>");
		return message;
	}
}
