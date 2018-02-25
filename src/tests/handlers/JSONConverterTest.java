package tests.handlers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.skyscreamer.jsonassert.*;

import handlers.json.JSONConverter;
import handlers.json.JSONConverter.Location;
import handlers.json.JSONConverter.Names;

import model.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import results.*;

public class JSONConverterTest {
	private JSONConverter jtest;
	
	@Before
	public void setUp() {
		jtest = new JSONConverter();
	}

	@After
	public void tearDown() {
		jtest = null;
	}
	
	@Test
	public void testPersonsToJSON() {
		PersonResult test = new PersonResult(new Person("123", "Andrew", "Werner", 'M', "dooder", "mtw", "cccmcsc", ""));
		//System.out.println(jtest.ObjectToJSON(test));
		
		Person[] data = {
				new Person("abc", "Fred", "Weasley", 'M', "ron", "arthur", "molly", ""),
				new Person("def", "George", "Weasley", 'M', "ron", "arthur", "molly", "")
		};
		PersonResult big = new PersonResult(data);
		//System.out.println(jtest.ObjectToJSON(big));
		
		PersonResult err = new PersonResult("error message");
		String check = "{\"message\":\"error message\"}";
		JSONAssert.assertEquals(check, jtest.ObjectToJSON(err), JSONCompareMode.STRICT);
	}
	
	@Test
	public void testEventsToJSON() {
		EventResult test = new EventResult(new Event("123", "klinc9", "dooder", 34.777, 90.23, "Finland", "Helsinki", "birth", "1866"));
		//System.out.println(jtest.ObjectToJSON(test));
		
		Event[] data = {
				new Event("258", "klinc9", "dooder", 12121, 90.23, "Russia", "Samara", "death", "1966"),
				new Event("343", "klinc9", "dooder", 667.1, 90.23, "USA", "Provo", "baptism", "1899")
		};
		EventResult big = new EventResult(data);
		//System.out.println(jtest.ObjectToJSON(big));
		
		EventResult err = new EventResult("something went wrong");
		String check = "{\"message\":\"something went wrong\"}";
		JSONAssert.assertEquals(check, jtest.ObjectToJSON(err), JSONCompareMode.STRICT);
	}
	
	@Test
	public void testAuthToJSON() {
		AuthResult test = new AuthResult(new AuthToken("1111", "cMonster", "v4rt5"));
		//System.out.println(jtest.ObjectToJSON(test));
		
		AuthResult ar = new AuthResult("there was a problem");
		String check = "{\"message\":\"there was a problem\"}";
		JSONAssert.assertEquals(check, jtest.ObjectToJSON(ar), JSONCompareMode.STRICT);
	}
	
	@Test
	public void testGetLocations() {
		Location[] test = jtest.GetLocations();
		assertTrue(test.length > 0);
	}
	
	@Test
	public void testGetMaleNames() throws IOException {
		String[] test = jtest.GetNames(Names.MALE);
		assertTrue(test.length > 0);
	}
	
	@Test
	public void testGetFemaleNames() throws IOException {
		String[] test = jtest.GetNames(Names.FEMALE);
		assertTrue(test.length > 0);
	}
	
	@Test
	public void testGetSurnames() throws IOException {
		String[] test = jtest.GetNames(Names.SURNAME);
		assertTrue(test.length > 0);
	}
}
