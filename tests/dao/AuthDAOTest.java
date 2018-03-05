package tests.dao;

import static org.junit.Assert.*;

import model.AuthToken;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.*;

public class AuthDAOTest {
	private AuthDAO ad;
	private Database db;

	@Before
	public void setUp() {
		Database.setTesting(true);
		db = new Database();
		ad = db.getAD();
	}

	@After
	public void tearDown() {
		db.closeConnection(false);
		ad = null;
		db = null;
		Database.setTesting(false);
	}

	@Test
	public void testConnection() {
		assertNotNull(ad.getConnection());
	}

	@Test
	public void testAddAuthToken() {
		AuthToken a = new AuthToken("1234", "geralt", "9999");
		try {
			ad.addAuthToken(a);
			AuthToken b = ad.getAuthToken(a.getAuthTokenID());
			assertEquals(a.getAuthTokenID(), b.getAuthTokenID());
			assertEquals(a.getUserName(), b.getUserName());
			assertEquals(a.getPersonID(), b.getPersonID());
		} catch (DatabaseException e) {
			fail("testAddAuthToken failed.");
		}
	}
	
	@Test(expected=DatabaseException.class)
	public void testDeleteAuthToken() throws DatabaseException{
		AuthToken a = new AuthToken("4040", "klinc", "hhhh");
		ad.addAuthToken(a);
		ad.deleteAuthToken(a);
		assertNotNull(ad.getAuthToken(a.getAuthTokenID()));
		fail("testDeleteAuthToken failed.");
	}
	
	@Test
	public void testDeleteAll() throws DatabaseException {
		AuthToken a = new AuthToken("22222", "cMonster", "b456nm");
		AuthToken b = new AuthToken("vafe", "zorlda", "77877");
		AuthToken c = new AuthToken("313", "chief", "343");
		
		ad.addAuthToken(a); ad.addAuthToken(b); ad.addAuthToken(c);
		try {
			ad.deleteAllAuthTokens();
			if(ad.getAllAuthTokens().length > 0) {
				throw new DatabaseException("getAllTokens failed.");
			}
		} catch (DatabaseException e) {
			fail("testDeleteAll failed.");
		}
	}
}
