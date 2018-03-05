package tests.handlers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Database;

public class FileHandlerTest {

	@Before
	public void setUp() {
		Database.setTesting(true);
	}
	
	@After
	public void tearDown() {
		Database.setTesting(false);
	}
	
	@Test
	public void testDefaultHandler() {
		String serverHost = "localhost";
		String serverPort = "8080";
		
		try {
			URL url = new URL("http://" + serverHost + ":" + serverPort + "/");
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestMethod("GET");
			http.setDoOutput(false);
			http.addRequestProperty("Accept", "text/html");
			http.connect();
			assertEquals(HttpURLConnection.HTTP_OK, http.getResponseCode());
		} catch (IOException io) {
			fail(io.getLocalizedMessage());
		}
	}
}
