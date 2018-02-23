package tests.model;

import static org.junit.Assert.*;

import model.Event;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventTest {
	private Event e = null;
	
	@Before
	public void setUp() { e = new Event(); }

	@After
	public void tearDown() { e = null; }

	@Test
	public void testEventID() { e.setEventID("1234"); assertEquals("1234", e.getEventID()); }

	@Test
	public void testPersonID() { e.setPersonID("bob"); assertEquals("bob", e.getPersonID()); }

	@Test
	public void testDescendant() { e.setDescendant("my child"); assertEquals("my child", e.getDescendant()); }

	@Test
	public void testLatitude() { e.setLatitude(28.5693); assertEquals(28.5693, e.getLatitude(), 0); }

	@Test
	public void testLongitude() { e.setLongitude(55.1147); assertEquals(55.1147, e.getLongitude(), 0); }

	@Test
	public void testCountry() { e.setCountry("Nilfgaard"); assertEquals("Nilfgaard", e.getCountry()); }

	@Test
	public void testCity() { e.setCity("Town"); assertEquals("Town", e.getCity()); }

	@Test
	public void testEventType() { e.setEventType("birth"); assertEquals("birth", e.getEventType()); }

	@Test
	public void testYear() { e.setYear("1451"); assertEquals("1451", e.getYear()); }
}
