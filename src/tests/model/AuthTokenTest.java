package tests.model;

import static org.junit.Assert.*;

import model.AuthToken;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AuthTokenTest {
	private AuthToken a = null;
	
	@Before
	public void setUp() { a = new AuthToken("1107654321", "CatfishProphecy", "abcd1234"); }
	
	@After
	public void tearDown() { a = null; }

	@Test
	public void testGetAuthTokenID() { assertEquals("1107654321", a.getAuthTokenID()); }

	@Test
	public void testSetAuthTokenID() { a.setAuthTokenID("hello"); assertEquals("hello", a.getAuthTokenID()); }

	@Test
	public void testGetUserName() { assertEquals("CatfishProphecy", a.getUserName()); }

	@Test
	public void testSetUserName() { a.setUserName("johnny"); assertEquals("johnny", a.getUserName()); }

	@Test
	public void testGetPersonId() { assertEquals("abcd1234", a.getPersonId()); }

	@Test
	public void testSetPersonId() { a.setPersonId("6"); assertEquals("6", a.getPersonId());}
}
