package tests.handlers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.*;

import org.junit.Test;

public class FileHandlerTest {

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
