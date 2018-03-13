package tests.handlers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.skyscreamer.jsonassert.*;

import handlers.json.JSONConverter;
import handlers.json.JSONConverter.Location;
import handlers.json.JSONConverter.Names;

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
	public void testObjectToJSON() {	
		PersonResult err = new PersonResult("error message");
		String check = "{\"message\":\"error message\"}";
		JSONAssert.assertEquals(check, jtest.ObjectToJSON(err), JSONCompareMode.STRICT);
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
