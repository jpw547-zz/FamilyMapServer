package tests.requests;

import static org.junit.Assert.*;

import model.Event;
import model.Person;
import model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import requests.LoadRequest;

public class LoadRequestTest {
	private LoadRequest lr;
	
	@Before
	public void setUp() {
		User[] u = new User[1];
		Person[] p = new Person[1];
		Event[] e = new Event[1];
		lr = new LoadRequest(u, p, e);
	}

	@After
	public void tearDown() {
		lr = null;
	}

	@Test
	public void testUserList() {
		User[] test = {
				new User("peter", "rock", "p@gmail.com", "Simon", "Peter", 'M'),
				new User("james", "oldie", "ja@gmail.com", "James", "Someone", 'M'),
				new User("john", "youngn", "jo@gmail.com", "John", "TheBeloved", 'M')
		};
		lr.setUserList(test);
		assertEquals("ja@gmail.com", lr.getUserList()[1].getEmail());
	}

	@Test
	public void testPersonList() {
		Person[] test = {
				new Person("123", "Huey", "Duck", 'M', "DD", "", "", ""),
				new Person("abc", "Dewey", "Duck", 'M', "DD", "", "", ""),
				new Person("xyz", "Louie", "Duck", 'M', "DD", "", "", "")
		};
		lr.setPersonList(test);
		assertEquals("Louie", lr.getPersonList()[2].getFirstName());
	}

	@Test
	public void testEventList() {
		Event[] test = {
				new Event("niea0", "123", "DD", 10.8, 66.789, "France", "Paris", "birth", "1999"),
				new Event("aama", "123", "DD", 55.456, 3.215, "Germany", "Berlin", "baptism", "2007"),
				new Event("nit43", "123", "DD", 66.664, -2.13, "USA", "Duckberg", "death", "2077")
		};
		lr.setEventList(test);
		assertEquals(-2.13, lr.getEventList()[2].getLongitude(), 0);
	}

}
