package tests.results;

import static org.junit.Assert.*;

import model.Person;

import org.junit.After;
import org.junit.Test;

import results.PersonResult;

public class PersonResultTest {
	private PersonResult pr;

	@After
	public void tearDown() {
		pr = null;
	}

	@Test
	public void testOnePerson() {
		Person test = new Person("123", "Andrew", "Werner", 'M', "dooder", "mtw", "cccmcsc", "");
		pr = new PersonResult(test);
		assertEquals(test.getDescendant(), pr.getPerson().getDescendant());
	}
	
	@Test
	public void testManyPersons() {
		Person[] test = {
				new Person("abc", "Fred", "Weasley", 'M', "ron", "arthur", "molly", ""),
				new Person("def", "George", "Weasley", 'M', "ron", "arthur", "molly", "")
		};
		pr = new PersonResult(test);
		assertEquals(test[1].getMother(), pr.getData()[1].getMother());
	}
	
	@Test
	public void testError() {
		pr = new PersonResult("something went wrong");
		assertEquals("something went wrong", pr.getMessage());
	}
}
