package tests.requests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import requests.FillRequest;

public class FillRequestTest {
	private FillRequest fr;

	@Before
	public void setUp() {
		fr = new FillRequest("cMonster", 4);
	}

	@After
	public void tearDown() {
		fr = null;
	}

	@Test
	public void testUsername() {
		assertEquals("cMonster", fr.getUsername());
		fr.setUsername("skywalker");
		assertEquals("skywalker", fr.getUsername());
	}

	@Test
	public void testGenerations() {
		assertEquals(4, fr.getGenerations());
		fr.setGenerations(6);
		assertEquals(6, fr.getGenerations());
	}
}
