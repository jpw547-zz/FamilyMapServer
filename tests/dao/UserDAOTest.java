package tests.dao;

import static org.junit.Assert.*;

import model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Database;
import dao.DatabaseException;
import dao.UserDAO;

public class UserDAOTest {
	private UserDAO ud;
	private Database db;

	@Before
	public void setUp() {
		Database.setTesting(true);
		db = new Database();
		ud = db.getUD();
	}

	@After
	public void tearDown() {
		db.closeConnection(false);
		ud = null;
		db = null;
		Database.setTesting(false);
	}

	@Test
	public void testConnection() {
		assertNotNull(ud.getConnection());
	}

	@Test
	public void testAddUser() {
		User a = new User("cheese", "apple", "myemail@gmail.com", "geralt", "of Rivia", 'M', "");
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
		User a = new User("tricycle", "helloWorld", "hardyhar@myldsmail.net", "taco", "master", 'F', "");
		User b = new User("tricycle", "nahnahnah", "hardyhar@myldsmail.net", "burrito", "master", 'F', "");
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
		User a = new User("silverado", "apple", "myemail@gmail.com", "geralt", "of Rivia", 'M', "");
		ud.addUser(a);
		ud.deleteUser(a);
		assertNotNull(ud.getUser(a.getUserName()));
		fail("testDeleteUser failed.");
	}
	
	@Test
	public void testDeleteAll() {
		User a = new User("xuther", "secret", "anemail@gmail.com", "Tom", "Jones", 'M', "");
		User b = new User("mustard", "ketchup", "onion@msn.com", "Tomato", "Johnson", 'F', "");
		User c = new User("cloudy", "darkness", "dragonofmist@gmail.com", "Farni", "Yokuora", 'M', "");
		
		try {
			ud.addUser(a);
			ud.addUser(b);
			ud.addUser(c);
			ud.deleteAllUsers();
			if(ud.getAllUsers().length > 0) {
				throw new DatabaseException("getAllUsers failed.");
			}
		} catch(DatabaseException e) {
			fail("testDeleteAllUsers failed.");
		}
	}
}
