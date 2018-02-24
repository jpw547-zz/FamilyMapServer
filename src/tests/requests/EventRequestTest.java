package tests.requests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import requests.EventRequest;

public class EventRequestTest {
	private EventRequest er;

	@Before
	public void setUp() {
		er = new EventRequest("3e3e3e", "nomite");
	}

	@After
	public void tearDown() {
		er = null;
	}

	@Test
	public void testAuthTokenID() {
		assertEquals("3e3e3e", er.getAuthTokenID());
		er.setAuthTokenID("ban00");
		assertEquals("ban00", er.getAuthTokenID());
	}

	@Test
	public void testEventID() {
		assertEquals("nomite", er.getEventID());
		er.setEventID("vaw");
		assertEquals("vaw", er.getEventID());
	}
}
