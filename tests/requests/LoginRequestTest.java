package tests.requests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import requests.LoginRequest;

public class LoginRequestTest {
	private LoginRequest lr;
	
	@Before
	public void setUp() {
		lr = new LoginRequest("cMonster", "tacos");
	}

	@After
	public void tearDown() {
		lr = null;
	}

	@Test
	public void testUsername() {
		lr.setUsername("chewbacca");
		assertEquals("chewbacca", lr.getUsername());
	}

	@Test
	public void testPassword() {
		lr.setPassword("open sesame");
		assertEquals("open sesame", lr.getPassword());
	}
}
