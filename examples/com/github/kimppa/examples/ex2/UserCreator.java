package com.github.kimppa.examples.ex2;

public class UserCreator {
	
	private SsnValidator ssnValidator;
	
	private UserDAO userDAO;
	
	public void registerUser(String username, String ssn) {
		if(!ssnValidator.validateSsn(ssn)) {
			throw new RuntimeException("Invalid SSN");
		}
		
		User user = new User();
		user.setUsername(username);
		user.setSsn(ssn);

		userDAO.save(user);
	}

}
