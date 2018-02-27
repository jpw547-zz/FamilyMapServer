package tests.services;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import requests.PersonRequest;
import requests.RegisterRequest;
import results.AuthResult;
import results.PersonResult;
import services.PersonService;
import services.RegisterService;

public class PersonServiceTest {
	private PersonService ps;
	private AuthResult ar;
	
	@Before
	public void setUp() {
		ps = new PersonService();
	}

	@After
	public void tearDown() {
		ps = null;
	}

	@Test
	public void testGetAll() {
		RegisterRequest rr = new RegisterRequest("jpw547", "manamana", "huffle@hogwarts.com", "John", "Werner", 'M');
		ar = new RegisterService().register(rr);
		
		PersonResult pr = ps.getAll(new PersonRequest(ar.getAuthToken().getAuthTokenID(), ar.getAuthToken().getPersonID()));
		
		if(pr.getData() == null) {
			System.out.println("WHYYYY???");
		}
		assertEquals(pr.getData().length, 31, 0);
	}

	@Test
	public void testGetPerson() {
		PersonResult pr = ps.getPerson(new PersonRequest(ar.getAuthToken().getAuthTokenID(), ar.getAuthToken().getPersonID()));
		
		assertEquals(pr.getPerson().getFirstName(), "John");
		assertEquals(pr.getPerson().getLastName(), "Werner");
	}
}
