package tests.dao;

import static org.junit.Assert.*;

import model.Event;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Database;
import dao.DatabaseException;
import dao.EventDAO;

public class EventDAOTest {
	private EventDAO ed;
	private Database db;

	@Before
	public void setUp() throws Exception {
		db = new Database();
		ed = db.getED();;
	}

	@After
	public void tearDown() throws Exception {
		db.closeConnection(false);
		ed = null;
		db = null;
	}

	@Test
	public void testConnection() {
		assertNotNull(ed.getConnection());
	}

	@Test
	public void testAddEvent() {
		Event a = new Event("1233", "Willy Wonka", "yourmom", 51.023, 48.269, "Brazil", "Sao Paulo", "birth", "1954");
		try {
			ed.addEvent(a);
			Event b = ed.getEvent(a.getEventID());
			assertEquals(a.getEventID(), b.getEventID());
			assertEquals(a.getPersonID(), b.getPersonID());
			assertEquals(a.getDescendant(), b.getDescendant());
			assertEquals(a.getLatitude(), b.getLatitude(), 0);
			assertEquals(a.getLongitude(), b.getLongitude(), 0);
			assertEquals(a.getCountry(), b.getCountry());
			assertEquals(a.getCity(), b.getCity());
			assertEquals(a.getEventType(), b.getEventType());
			assertEquals(a.getYear(), b.getYear());
		} catch (DatabaseException e) {
			fail("testAddEvent failed. " + e.getMessage());
		}
	}
	
	@Test
	public void testModifyEvent() {
		Event a = new Event("9977", "Darth Vader", "yourmom", 51.066, 48.269, "USA", "Chicago", "baptism", "1986");
		Event a1 = new Event("9977", "Sirius Black", "yourmom", 57.959, 2.344, "USA", "Orlando", "baptism", "1977");
		try {
			ed.addEvent(a);
			ed.modifyEvent(a1);
			Event b = ed.getEvent(a.getEventID());
			assertEquals(a1.getEventID(), b.getEventID());
			assertEquals(a1.getPersonID(), b.getPersonID());
			assertEquals(a1.getDescendant(), b.getDescendant());
			assertEquals(a1.getLatitude(), b.getLatitude(), 0);
			assertEquals(a1.getLongitude(), b.getLongitude(), 0);
			assertEquals(a1.getCountry(), b.getCountry());
			assertEquals(a1.getCity(), b.getCity());
			assertEquals(a1.getEventType(), b.getEventType());
			assertEquals(a1.getYear(), b.getYear());
		} catch (DatabaseException e) {
			fail("testModifyEvent failed. " + e.getMessage());
		}
	}
	
	@Test(expected=DatabaseException.class)
	public void testDeleteEvent() throws DatabaseException{
		Event a = new Event("sbrsh", "Sylvester Stallone", "yourmom", 32.023, 11.559, "England", "London", "christening", "1999");
		ed.addEvent(a);
		ed.deleteEvent(a);
		assertNotNull(ed.getEvent(a.getEventID()));
		fail("testDeleteEvent failed.");
	}
	
	@Test
	public void testDeleteAll() throws DatabaseException {
		Event a = new Event("22222", "223242", "cMonster", 10.191, 77.004, "Ireland", "Dublin", "birth", "1901");
		Event b = new Event("vafe", "223242", "cMonster", 88.900, 43.121, "Argentina", "Buenos Aires", "baptism", "1909");
		Event c = new Event("313", "chief", "cMonster", 3.411, 101.23, "China", "Beijing", "death", "1999");
		
		ed.addEvent(a); ed.addEvent(b); ed.addEvent(c);
		try {
			ed.deleteAllEvents();
			if(ed.getAllEvents("cMonster").length > 0) {
				throw new DatabaseException("getAllEvents failed.");
			}
		} catch (DatabaseException e) {
			fail("testDeleteAllEvents failed.");
		}
	}
}
