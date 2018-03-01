package tests.services;

import static org.junit.Assert.*;

import model.Event;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.*;
import results.*;
import services.*;
import requests.*;

public class EventServiceTest {
	private EventService es;

	@Before
	public void setUp() {
		es = new EventService();
	}

	@After
	public void tearDown() {
		es = null;
	}

	@Test
	public void testGetAll() {
		RegisterRequest req = new RegisterRequest("gMonster", "gremlins", "dumblydore@hogwarts.edu", "Albus", "Dumbledore", 'M');
		AuthResult ar = new RegisterService().register(req);
		
		Event a = new Event("ewr", "223242", "gMonster", 10.191, 77.004, "Ireland", "Dublin", "birth", "1901");
		Event b = new Event("vbnm", "223242", "gMonster", 88.900, 43.121, "Argentina", "Buenos Aires", "baptism", "1909");
		Event c = new Event("32", "chief", "gMonster", 3.411, 101.23, "China", "Beijing", "death", "1999");
		
		Database db = new Database();
		
		try {
			db.getED().addEvent(a);
			db.getED().addEvent(b);
			db.getED().addEvent(c);
			db.closeConnection(true);
		} catch (DatabaseException e) {
			db.closeConnection(false);
			System.out.println(e.getLocalizedMessage());
		}
		
		EventRequest er = new EventRequest(ar.getAuthToken().getAuthTokenID(), "");
		assertEquals(4, es.getAll(er).getData().length);
	}

	@Test
	public void testGetEvent() {
		Event a = new Event("faltg", "minty", "gMonster", 10.191, 77.004, "Iceland", "Yyyvsk", "birth", "1777");

		Database db = new Database();
		
		try {
			db.getED().addEvent(a);
			db.closeConnection(true);
		} catch (DatabaseException e) {
			db.closeConnection(false);
			System.out.println(e.getLocalizedMessage());
		}
		
		EventRequest er = new EventRequest("", "faltg");
		assertEquals("1777", es.getEvent(er).getEvent().getYear());
	}
}
