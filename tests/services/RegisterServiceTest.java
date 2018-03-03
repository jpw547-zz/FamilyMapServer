package tests.services;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import requests.RegisterRequest;
import results.AuthResult;
import services.ClearService;
import services.RegisterService;

public class RegisterServiceTest {
	private RegisterService rs;

	@Before
	public void setUp() {
		new ClearService().clear();
		rs = new RegisterService();
	}

	@After
	public void tearDown() {
		rs = null;
	}

	@Test
	public void testRegister() {
		RegisterRequest rr = new RegisterRequest("cMonster", "apple", "seeker@hogwarts.com", "Harry", "Potter", 'M');
		AuthResult ar = rs.register(rr);
		assertEquals(rr.getUserName(), ar.getAuthToken().getUserName());
	}
}
