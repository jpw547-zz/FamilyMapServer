package tests.requests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import requests.RegisterRequest;

public class RegisterRequestTest {
	private RegisterRequest rr;
	
	@Before
	public void setUp() {
		rr = new RegisterRequest("cMonster", "wakawaka", "me@yahoo.com", "John", "Doe", 'M');
	}

	@After
	public void tearDown() {
		rr = null;
	}

	@Test
	public void testUserName() {
		rr.setUserName("boogieman");
		assertEquals("boogieman", rr.getUserName());
	}

	@Test
	public void testPassword() {
		rr.setPassword("humbug");
		assertEquals("humbug", rr.getPassword());
	}

	@Test
	public void testEmail() {
		rr.setEmail("hansolo@gmail.com");
		assertEquals("hansolo@gmail.com", rr.getEmail());
	}

	@Test
	public void testFirstName() {
		rr.setFirstName("Timmy");
		assertEquals("Timmy", rr.getFirstName());
	}

	@Test
	public void testLastName() {
		rr.setLastName("Turner");
		assertEquals("Turner", rr.getLastName());
	}

	@Test
	public void testGender() {
		rr.setGender('F');
		assertEquals('F', rr.getGender());
	}
}
