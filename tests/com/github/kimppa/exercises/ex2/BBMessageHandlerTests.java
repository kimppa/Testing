package com.github.kimppa.exercises.ex2;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BBMessageHandlerTests {

	@Mock
	private Config config;

	@Mock
	private BBCodeParser bbCodeParser;

	@Mock
	private BBHtmlConverter bbHtmlConverter;

	@InjectMocks
	private BBMessageHandler bbMessageHandler;

	@Before
	public void setUp() {
		bbMessageHandler = new BBMessageHandler();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void handleMessage_htmlCodeNotAllowed_converterCalled() {
		String inputString = "<b>bolded</b>";
		Mockito.when(config.allowHtml()).thenReturn(false);
		Mockito.when(bbHtmlConverter.replaceTagsWithHtmlEntities(inputString))
				.thenReturn("&lt;b&gt;bolded&lt;/b&gt;");
		Mockito.when(bbCodeParser.parseBBCodes(Mockito.anyString()))
				.thenReturn("&lt;b&gt;bolded&lt;/b&gt;");

		String message = bbMessageHandler.handleMessage(inputString);
		Assert.assertEquals("&lt;b&gt;bolded&lt;/b&gt;", message);

		Mockito.verify(bbHtmlConverter, Mockito.times(1))
				.replaceTagsWithHtmlEntities(inputString);
	}
	
	@Test
	public void handleMessage_htmlCodeAreAllowed_converterNotCalled() {
		String inputString = "<b>bolded</b>";
		Mockito.when(config.allowHtml()).thenReturn(true);
		Mockito.when(bbHtmlConverter.replaceTagsWithHtmlEntities(inputString))
		.thenReturn("&lt;b&gt;bolded&lt;/b&gt;");
		Mockito.when(bbCodeParser.parseBBCodes(Mockito.anyString()))
		.thenReturn("&lt;b&gt;bolded&lt;/b&gt;");
		
		String message = bbMessageHandler.handleMessage(inputString);
		Assert.assertEquals("&lt;b&gt;bolded&lt;/b&gt;", message);
		
		Mockito.verify(bbHtmlConverter, Mockito.times(0))
		.replaceTagsWithHtmlEntities(inputString);
	}
	
	@Test
	public void handleMessage_anyInput_bbCodeParserCalled() {
		String inputString = "test";
		Mockito.when(config.allowHtml()).thenReturn(true);
		Mockito.when(bbHtmlConverter.replaceTagsWithHtmlEntities(inputString))
		.thenReturn("test");
		Mockito.when(bbCodeParser.parseBBCodes(Mockito.anyString()))
		.thenReturn("test2");
		
		String message = bbMessageHandler.handleMessage(inputString);
		Assert.assertEquals("test2", message);
		
		Mockito.verify(bbCodeParser, Mockito.times(1))
		.parseBBCodes(Mockito.anyString());
	}
	
	@Test
	public void handleMessage_htmlNotAllowed_verifyCallOrder() {
		Mockito.when(config.allowHtml()).thenReturn(false);
		Mockito.when(bbHtmlConverter.replaceTagsWithHtmlEntities(Mockito.anyString()))
		.thenReturn("test");
		Mockito.when(bbCodeParser.parseBBCodes(Mockito.anyString()))
		.thenReturn("test2");
		
		InOrder inOrder = Mockito.inOrder(bbHtmlConverter, bbCodeParser);
		
		String message = bbMessageHandler.handleMessage("test");
		Assert.assertEquals("test2", message);
		
		inOrder.verify(bbHtmlConverter, Mockito.times(1))
		.replaceTagsWithHtmlEntities(Mockito.anyString());
		inOrder.verify(bbCodeParser, Mockito.times(1))
		.parseBBCodes(Mockito.anyString());
	}

}
