package com.github.kimppa.exercises.refactor.after;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.kimppa.exercises.refactor.Invoice;

public class InvoicerIntegrationTests {

	private static final Integer USER_1 = 1;
	private static final Integer USER_2 = 2;

	@Test
	public void InvoicerShouldReturnListOfInvoices() {
		Invoicer invoicer = new Invoicer();
		List<Invoice> invoices = invoicer.createInvoicesForUsers(getUsers());
		
		assertEquals(2, invoices.size());
	}

	private List<Integer> getUsers() {
		List<Integer> users = new ArrayList<Integer>();
		users.add(USER_1);
		users.add(USER_2);
		return users;
	}
}
