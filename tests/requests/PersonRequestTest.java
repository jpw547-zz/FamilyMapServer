package tests.requests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import requests.PersonRequest;

public class PersonRequestTest {
	private PersonRequest pr;
	
	@Before
	public void setUp() {
		pr = new PersonRequest("12345", "abat67");
	}

	@After
	public void tearDown() {
		pr = null;
	}

	@Test
	public void testAuthTokenID() {
		pr.setAuthTokenID("zzzzxz");
		assertEquals("zzzzxz", pr.getAuthTokenID());
	}

	@Test
	public void testPersonID() {
		pr.setPersonID("79bnam");
		assertEquals("79bnam", pr.getPersonID());
	}

}
