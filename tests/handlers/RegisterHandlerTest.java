package tests.handlers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Database;

import services.ClearService;

public class RegisterHandlerTest {

	@Before
	public void setUp() {
		Database.setTesting(true);
	}
	
	@After
	public void tearDown() {
		Database.setTesting(false);
	}
	
	@Test
	public void testRegisterHandler() {
		String serverHost = "localhost";
		String serverPort = "8080";
		
		try {
			//First clear to make sure the user can be registered.
			new ClearService().clear();
			
			URL url = new URL("http://" + serverHost + ":" + serverPort + "/user/register");
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.addRequestProperty("Accept", "application/json");
			http.connect();
			String reqData = "{" +
				"\"userName\": \"susan\"," +
				"\"password\": \"mysecret\"," +
				"\"email\": \"susan@gmail.com\"," +
				"\"firstName\": \"Susan\"," +
				"\"lastName\": \"Ellis\"," +
				"\"gender\": \"f\"" +
			"}";
			OutputStream reqBody = http.getOutputStream();
			OutputStreamWriter out = new OutputStreamWriter(reqBody);
			out.write(reqData);
			out.flush();
			reqBody.close();
			assertEquals(http.getResponseCode(), HttpURLConnection.HTTP_OK);
			InputStream respBody = http.getInputStream();
			assertTrue(readString(respBody).contains("susan"));
		} catch (IOException io) {
			fail(io.getLocalizedMessage());
		}
	}
	
	private static String readString(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		InputStreamReader sr = new InputStreamReader(is);
		char[] buf = new char[1024];
		int len;
		while ((len = sr.read(buf)) > 0) {
			sb.append(buf, 0, len);
		}
		return sb.toString();
	}
}
