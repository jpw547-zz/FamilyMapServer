package tests.results;

import static org.junit.Assert.*;

import model.AuthToken;

import org.junit.After;
import org.junit.Test;

import results.AuthResult;

public class AuthResultTest {
	private AuthResult ar;

	@After
	public void tearDown() {
		ar = null;
	}

	@Test
	public void testAuthToken() {
		AuthToken a = new AuthToken("1111", "cMonster", "v4rt5");
		ar = new AuthResult(a);
		assertEquals(a.getUserName(), ar.getAuthToken().getUserName());
	}
	
	@Test
	public void testError() {
		ar = new AuthResult("there was a problem");
		assertEquals("there was a problem", ar.getMessage());
	}
}
