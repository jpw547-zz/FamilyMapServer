package tests.dao;

import static org.junit.Assert.*;

import model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DatabaseException;
import dao.UserDAO;

public class UserDAOTest {

	private UserDAO ud = null;

	@Before
	public void setUp() throws Exception {
		ud = new UserDAO();
		ud.setConnection();
	}

	@After
	public void tearDown() throws Exception {
		ud.closeConnection(true);
		ud = null;
	}

	@Test
	public void testConnection() {
		assertNotNull(ud.getConnection());
	}

	@Test
	public void testAddUser() {
		User a = new User("cheese", "apple", "myemail@gmail.com", "geralt", "of Rivia", 'M');
		try {
			ud.addUser(a);
			User b = ud.getUser(a.getUserName());
			assertEquals(a.getUserName(), b.getUserName());
			assertEquals(a.getPassword(), b.getPassword());
			assertEquals(a.getEmail(), b.getEmail());
			assertEquals(a.getFirstName(), b.getFirstName());
			assertEquals(a.getLastName(), b.getLastName());
			assertEquals(a.getGender(), b.getGender());
		} catch (DatabaseException e) {
			fail("testAddUser failed.");
		}
	}
	
	@Test
	public void testModifyUser() {
		User a = new User("tricycle", "helloWorld", "hardyhar@myldsmail.net", "taco", "master", 'F');
		User b = new User("tricycle", "nahnahnah", "hardyhar@myldsmail.net", "burrito", "master", 'F');
		try {
			ud.addUser(a);
			ud.modifyUser(b);
			User c = ud.getUser(a.getUserName());
			assertEquals(b.getUserName(), c.getUserName());
			assertEquals(b.getPassword(), c.getPassword());
			assertEquals(b.getEmail(), c.getEmail());
			assertEquals(b.getFirstName(), c.getFirstName());
			assertEquals(b.getLastName(), c.getLastName());
			assertEquals(b.getGender(), c.getGender());
		} catch (DatabaseException e) {
			fail("testModifyUser failed. " + e.getMessage());
		}
	}
	
	@Test(expected=DatabaseException.class)
	public void testDeleteUser() throws DatabaseException {
		User a = new User("silverado", "apple", "myemail@gmail.com", "geralt", "of Rivia", 'M');
		ud.addUser(a);
		ud.deleteUser(a);
		assertNotNull(ud.getUser(a.getUserName()));
		fail("testDeleteUser failed.");
	}
	
	
}
