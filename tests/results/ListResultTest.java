package tests.results;

import static org.junit.Assert.*;

import model.Event;
import model.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import results.ListResult;

public class ListResultTest {
	private ListResult lr;

	@Before
	public void setUp() {
		
	}
	@After
	public void tearDown() throws Exception {
		lr = null;
	}

	@Test
	public void testManyPersons() {
		Person[] per = {
				new Person("44115163457", "Wendy", "Bird", 'F', "bisquick", "mmnonaig", "9835135210", ""),
				new Person("aambineoale", "Misty", "Mango", 'F', "bisquick", "agaganiiom", "9835135210", "")
		};
		
		Event[] evt = {
				new Event("bn-mi80", "555mno0", "zMonster", 33.569, 9.3, "Thailand", "Bangkok", "death", "2066"),
				new Event("9989532-qwe", "aambineoale", "bisquick", -6.3356, -118.2, "USA", "Crestline", "baptism", "2019")
		};
		lr = new ListResult(per, evt);
		assertEquals(per[1].getMother(), lr.getPersonList()[1].getMother());
		assertEquals(evt[0].getCity(), lr.getEventList()[0].getCity());
	}
	
	@Test
	public void testError() {
		lr = new ListResult("something went wrong");
		assertEquals("something went wrong", lr.getMessage());
	}

}
