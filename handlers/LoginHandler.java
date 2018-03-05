package handlers;

import handlers.json.JSONConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import requests.LoginRequest;
import results.AuthResult;
import services.LoginService;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoginHandler implements HttpHandler {

	private AuthResult error;
	
	public void handle(HttpExchange exch) throws IOException {
		boolean success = false;
		JSONConverter json = new JSONConverter();
		
		try {
			if(exch.getRequestMethod().toUpperCase().equals("POST")) {
				InputStream reqBody = exch.getRequestBody();
				InputStreamReader in = new InputStreamReader(reqBody);
				LoginRequest lr = json.JSONToObject(in, LoginRequest.class);
				if(validateRequestInfo(lr)) {
					AuthResult ar = new LoginService().login(lr);
					exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
					OutputStream respBody = exch.getResponseBody();
					OutputStreamWriter out = new OutputStreamWriter(respBody);
					out.write(json.ObjectToJSON(ar));
					out.flush();
					respBody.close();
					success = true;
				}
			}
			
			if(!success) {
				exch.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				if(error != null) {
					OutputStreamWriter out = new OutputStreamWriter(exch.getResponseBody());
					out.write(json.ObjectToJSON(error));
					out.flush();
				}
				exch.getResponseBody().close();
			}
		} catch (IOException io) {
			exch.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
			exch.getResponseBody().close();
			io.printStackTrace();
		}
	}
	
	private boolean validateRequestInfo(LoginRequest lr) {
		if(lr.getUsername().isEmpty()) { error = new AuthResult("Username empty"); return false; }
		if(lr.getPassword().isEmpty()) { error = new AuthResult("Password empty"); return false; }

		//At this point everything checks out.
		return true;
	}

}
