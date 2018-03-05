package tests.handlers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Database;

public class ClearHandlerTest {

	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
		Database.setTesting(false);
	}
	
	@Test
	public void testClearHandler() {
		Database.setTesting(true);

		String serverHost = "localhost";
		String serverPort = "8080";
		
		try {
			URL url = new URL("http://" + serverHost + ":" + serverPort + "/clear");
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(false);
			http.addRequestProperty("Accept", "application/json");
			http.connect();
			assertEquals(http.getResponseCode(), HttpURLConnection.HTTP_OK);
		} catch (IOException io) {
			fail(io.getLocalizedMessage());
		}
	}
}
