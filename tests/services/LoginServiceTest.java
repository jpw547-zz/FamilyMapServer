package tests.services;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DatabaseException;
import dao.UserDAO;

import requests.LoginRequest;
import results.AuthResult;
import services.LoginService;

public class LoginServiceTest {
	private LoginService ls;

	@Before
	public void setUp() {
		ls = new LoginService();
	}

	@After
	public void tearDown() {
		ls = null;
	}

	@Test
	public void testLogin() {
		LoginRequest req = new LoginRequest("cMonster", "pass");
		AuthResult res = ls.login(req);
		assertEquals("The user's information did not match information in the database. ::Login::", res.getMessage());
		
		UserDAO ud = new UserDAO();
		ud.setConnection();
		try {
			ud.addUser(new model.User("cMonster", "pass", "clarky@apple.com", "Clark", "Green", 'M', "cani"));
			ud.closeConnection(true);
		} catch (DatabaseException e) {
			try {
				ud.closeConnection(false);
			} catch (DatabaseException e1) {
				System.out.println("Connection close for Login Test failed.");
				e1.printStackTrace();
			}
			System.out.println("Add for Login Test failed.");
			e.printStackTrace();
		}
		LoginRequest req2 = new LoginRequest("cMonster", "pass"); 
		AuthResult check2 = ls.login(req2);
		if(req2.getUsername() == null) { System.out.println("REQ2"); }
		if(check2 == null) { System.out.println("RESULT"); }
		if(check2.getAuthToken() == null) { System.out.println("AUTH"); System.out.println(check2.getMessage()); }
		if(check2.getAuthToken().getUserName() == null) { System.out.println("USERNAME"); }
		assertEquals(req2.getUsername(), check2.getAuthToken().getUserName());
	}
}
