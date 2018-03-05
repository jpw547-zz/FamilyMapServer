package handlers;

import handlers.json.JSONConverter;

import java.io.*;
import java.net.HttpURLConnection;

import services.*;
import requests.*;
import results.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RegisterHandler implements HttpHandler {

	private AuthResult error;
	
	public void handle(HttpExchange exch) throws IOException {
		boolean success = false;
		JSONConverter json = new JSONConverter();
		
		try {
			if(exch.getRequestMethod().toUpperCase().equals("POST")) {
				InputStream reqBody = exch.getRequestBody();
				InputStreamReader in = new InputStreamReader(reqBody);
				RegisterRequest rr = json.JSONToObject(in, RegisterRequest.class);
				if(validateRequestInfo(rr)) {
					AuthResult ar = new RegisterService().register(rr);
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
	
	private boolean validateRequestInfo(RegisterRequest rr) {
		if(rr.getUserName().isEmpty()) { error = new AuthResult("Username empty"); return false; }
		if(rr.getPassword().isEmpty()) { error = new AuthResult("Password empty"); return false; }
		if(rr.getEmail().isEmpty()) { error = new AuthResult("Email empty"); return false; }
		if(rr.getFirstName().isEmpty()) { error = new AuthResult("First name empty"); return false; }
		if(rr.getLastName().isEmpty()) { error = new AuthResult("Last name empty"); return false; }
		if(rr.getGender() == 'f' || rr.getGender() == 'F') {
			rr.setGender('F');
		}
		if(rr.getGender() == 'm' || rr.getGender() == 'M') {
			rr.setGender('M');
		}
		if(rr.getGender() != 'M' && rr.getGender() != 'F') { error = new AuthResult("Gender invalid"); return false; }
		
		//At this point everything checks out.
		return true;
	}
}
