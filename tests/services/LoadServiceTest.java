package tests.services;

import static org.junit.Assert.*;

import model.Event;
import model.Person;
import model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import requests.LoadRequest;
import results.Result;
import services.ClearService;
import services.LoadService;

public class LoadServiceTest {
	private LoadService ls;

	@Before
	public void setUp() {
		new ClearService().clear();
		ls = new LoadService();
	}

	@After
	public void tearDown() {
		ls = null;
	}

	@Test
	public void testLoad() {
		User[] testU = {
				new User("peter", "rock", "p@gmail.com", "Simon", "Peter", 'M', ""),
				new User("james", "oldie", "ja@gmail.com", "James", "Someone", 'M', ""),
				new User("john", "youngn", "jo@gmail.com", "John", "TheBeloved", 'M', "")
		};
		Person[] testP = {
				new Person("123", "Huey", "Duck", 'M', "DD", "", "", ""),
				new Person("abc", "Dewey", "Duck", 'M', "DD", "", "", ""),
				new Person("xyz", "Louie", "Duck", 'M', "DD", "", "", "")
		};
		Event[] testE = {
				new Event("niea0", "123", "DD", 10.8, 66.789, "France", "Paris", "birth", "1999"),
				new Event("aama", "123", "DD", 55.456, 3.215, "Germany", "Berlin", "baptism", "2007"),
				new Event("nit43", "123", "DD", 66.664, -2.13, "USA", "Duckberg", "death", "2077")
		};
		
		Result res = ls.load(new LoadRequest(testU, testP, testE));
		assertTrue(res.getMessage().contains("Successfully"));
	}
}
