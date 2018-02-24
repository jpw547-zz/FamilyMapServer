package tests.results;

import static org.junit.Assert.*;

import model.Event;

import org.junit.After;
import org.junit.Test;

import results.EventResult;

public class EventResultTest {
	private EventResult er;

	@After
	public void tearDown() {
		er = null;
	}

	@Test
	public void testOneEvent() {
		Event test = new Event("123", "klinc9", "dooder", 34.777, 90.23, "Finland", "Helsinki", "birth", "1866");
		er = new EventResult(test);
		assertEquals(test.getCountry(), er.getEvent().getCountry());
	}
	
	@Test
	public void testManyEvents() {
		Event[] test = {
				new Event("258", "klinc9", "dooder", 12121, 90.23, "Russia", "Samara", "death", "1966"),
				new Event("343", "klinc9", "dooder", 667.1, 90.23, "USA", "Provo", "baptism", "1899")
		};
		er = new EventResult(test);
		assertEquals(test[1].getCity(), er.getData()[1].getCity());
	}
	
	@Test
	public void testError() {
		er = new EventResult("something went wrong");
		assertEquals("something went wrong", er.getMessage());
	}
}
