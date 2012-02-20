package com.github.kimppa.exercises.ex2;

import junit.framework.Assert;

import org.junit.Test;

public class BBMessageHandlerIntergrationTests {
	
	@Test
	public void handleMessage_inputContainsHtmlAndBBtags_htmlStrippedAndBBtagsReplaced() {
		String message = "<b>this</b> is [u]a message[/u]";
		message = new BBMessageHandler().handleMessage(message);
		Assert.assertEquals("&lt;b&gt;this&lt;/b&gt; is <u>a message</u>", message);
	}

}
