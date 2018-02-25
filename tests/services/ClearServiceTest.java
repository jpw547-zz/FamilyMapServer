package tests.services;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import results.Result;
import services.ClearService;

public class ClearServiceTest {
	private ClearService cs;

	@Before
	public void setUp() {
		cs = new ClearService();
	}

	@After
	public void tearDown() {
		cs = null;
	}

	@Test
	public void testClear() {
		Result message = cs.clear();
		assertEquals("Clear succeeded.", message.getMessage());
	}
}
