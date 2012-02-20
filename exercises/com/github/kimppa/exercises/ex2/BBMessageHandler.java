package com.github.kimppa.exercises.ex2;

public class BBMessageHandler {
	
	private BBHtmlConverter bbHtmlConverter = new BBHtmlConverter();
	
	private BBCodeParser bbCodeParser = new BBCodeParser();
	
	private Config config = new Config();

	/**
	 * Returns an HTML formated message. Parses BB code into HTML and if user
	 * entered HTML is disabled, then replaces those with tags with their HTML
	 * entity equivalents.
	 * 
	 * @param message
	 * @return
	 */
	public String handleMessage(String message) {
		if(!config.allowHtml()) {
			message = bbHtmlConverter.replaceTagsWithHtmlEntities(message);
		}

		message = bbCodeParser.parseBBCodes(message);
		return message;
	}

}
