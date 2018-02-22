package tests.model;

import static org.junit.Assert.*;

import model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private User u = null;

	@Before
	public void setUp() throws Exception { u = new User(); }

	@After
	public void tearDown() throws Exception { u = null; }

	@Test
	public void testUserName() { u.setUserName("littlekidlover"); assertEquals("littlekidlover", u.getUserName()); }

	@Test
	public void testPassword() { u.setPassword("open sesame"); assertEquals("open sesame", u.getPassword()); }

	@Test
	public void testEmail() { u.setEmail("4BYU2SPAM@gmail.com"); assertEquals("4BYU2SPAM@gmail.com", u.getEmail()); }

	@Test
	public void testFirstName() { u.setFirstName("Michael"); assertEquals("Michael", u.getFirstName()); }

	@Test
	public void testLastName() { u.setLastName("Scott"); assertEquals("Scott", u.getLastName()); }

	@Test
	public void testGender() { u.setGender('F'); assertEquals('F', u.getGender()); }
}
