package tests.handlers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class ClearHandlerTest {

	@Test
	public void testClearHandler() {
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
