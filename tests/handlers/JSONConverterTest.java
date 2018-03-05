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

import requests.LoadRequest;
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
	public void testJSONToObject() {
		String check = "{" +
	"\"users\":[" +
		"{"+
	"\"userName\":\"jpw547\","+
	"\"password\":\"johnny\","+
	"\"email\":\"jpw@byu.edu\","+
	"\"firstName\":\"John\","+
	"\"lastName\":\"Werner\","+
	 "\"gender\":\"M\","+
"\"personID\":\"33\""+
"}"+
	"],"+
	"\"persons\":["+
		"{"+
			
      "\"personID\": \"184ff0a5-c1c9-45a3-93f6-80f6becc2766\","+
			
      "\"firstName\": \"Malcom\","+
			
      "\"lastName\": \"Lastname\","+
			
      "\"gender\": \"M\","+
			
      "\"descendant\": \"jason\","+
			
      "\"father\": \"\","+
			
      "\"mother\": \"\","+
			
      "\"spouse\": \"f78b18de-8d0b-4128-81b5-eb4da2b11181\""+
    
		"},"+
		
    "{"+
			
      "\"personID\": \"f78b18de-8d0b-4128-81b5-eb4da2b11181\","+
			
      "\"firstName\": \"Tera\","+
			
      "\"lastName\": \"Mcdougald\","+
			
      "\"gender\": \"F\","+
			
      "\"descendant\": \"jason\","+
			
      "\"father\": \"\","+
			
      "\"mother\": \"\","+
			
      "\"spouse\": \"184ff0a5-c1c9-45a3-93f6-80f6becc2766\""+
    
		"}"+
	"],"+
	"\"events\":["+
		 "{"+
			
      "\"eventID\": \"a62d1281-816d-4b72-b988-1061776c01dc\","+
			
      "\"personID\": \"71cbd1fe-c0d7-4406-9794-109270cd623e\","+
			
      "\"descendant\": \"jason\","+
			
      "\"latitude\": 46.4667,"+
			
      "\"longitude\": 30.7333,"+
			
      "\"country\": \"Ukraine\","+
			
      "\"city\": \"Odessa\","+
			
      "\"eventType\": \"death\","+
			
      "\"year\": \"1895\""+
    
		"},"+
		
    "{"+
			
      "\"eventID\": \"fe4b0628-c732-4c18-b284-41f715c46141\","+
			
      "\"personID\": \"c7050b7f-9933-44d1-9302-0ceef1e87943\","+
			
      "\"descendant\": \"jason\","+
			
      "\"latitude\": 52.4,"+
			
      "\"longitude\": 16.9167,"+
			
      "\"country\": \"Poland\","+
			
      "\"city\": \"Poznań\","+
			
      "\"eventType\": \"birth\","+
			
      "\"year\": \"1780\""+
    
		"},"+
		
    "{"+
			
      "\"eventID\": \"31a2c75f-4c45-40b6-beea-e95bded95b23\","+
			
      "\"personID\": \"9fb51f2f-59e9-4b24-b8fa-0722aa014a6b\","+
			
      "\"descendant\": \"jason\","+
			
      "\"latitude\": 30.7,"+
			
      "\"longitude\": -87.95,"+
			
      "\"country\": \"United States\","+
			
      "\"city\": \"Mobile\","+
			
      "\"eventType\": \"birth\","+
			
      "\"year\": \"1786\""+
    
		"}"+
	"]"+
"}";
		LoadRequest lr = jtest.JSONToObject(check, LoadRequest.class);
		assertTrue(lr.getUserList().length > 0);
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
