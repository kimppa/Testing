package com.github.kimppa.examples.ex2;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserCreatorTests {

	
	private static final String SSN = "some ssn";

	private static final String USERNAME = "username";

	@Mock
	private UserDAO userDAO;

	@Mock
	private SsnValidator ssnValidator;

	@InjectMocks
	private UserCreator userCreator;

	@Before
	public void setUp() {
		userCreator = new UserCreator();
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = RuntimeException.class)
	public void registerUser_invalidSsn_userNotSave() {
		Mockito.when(ssnValidator.validateSsn(Mockito.anyString())).thenReturn(
				false);

		try {
				userCreator.registerUser(USERNAME, SSN);
		} finally {
			Mockito.verify(ssnValidator, Mockito.times(1)).validateSsn(
					Mockito.anyString());
			Mockito.verify(userDAO, Mockito.times(0)).save(
					Mockito.any(User.class));
		}
	}

	@Test
	public void registerUser_validSsn_userSave() {
		Mockito.when(ssnValidator.validateSsn(Mockito.anyString())).thenReturn(
				true);
		Mockito.doNothing().when(userDAO).save(Mockito.any(User.class));

		userCreator.registerUser(USERNAME, SSN);

		Mockito.verify(ssnValidator, Mockito.times(1)).validateSsn(SSN);
		Mockito.verify(userDAO, Mockito.times(1)).save(Mockito.any(User.class));
	}

	@Test
	public void registerUser_validSsn_userObjectPopulatedCorrectly() {
		Mockito.when(ssnValidator.validateSsn(Mockito.anyString())).thenReturn(true);
		Mockito.doNothing().when(userDAO).save(Mockito.any(User.class));

		userCreator.registerUser(USERNAME, SSN);

		ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
		Mockito.verify(userDAO).save(argument.capture());
		Assert.assertEquals(USERNAME, argument.getValue().getUsername());
		Assert.assertEquals(SSN, argument.getValue().getSsn());

	}

}
