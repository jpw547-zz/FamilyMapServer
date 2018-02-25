package tests.results;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import results.Result;

public class ResultTest {
	private Result r;

	@Before
	public void setUp() throws Exception {
		r = new Result();
	}

	@After
	public void tearDown() throws Exception {
		r = null;
	}

	@Test
	public void testMessage() {
		assertNull(r.getMessage());
		r.setMessage("hello");
		assertEquals("hello", r.getMessage());
	}

}
