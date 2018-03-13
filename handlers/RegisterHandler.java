package handlers;

import handlers.json.JSONConverter;

import java.io.*;
import java.net.HttpURLConnection;

import services.*;
import requests.*;
import results.*;

import com.sun.net.httpserver.*;

public class RegisterHandler implements HttpHandler {
	/**A generic Result to be returned in the event of an error.*/
	private Result error;
	
	/**The handler to register a user with the server.*/
	public void handle(HttpExchange exch) throws IOException {
		boolean success = false;
		JSONConverter json = new JSONConverter();
		
		try {
			//Accept only POST methods.
			if(exch.getRequestMethod().toUpperCase().equals("POST")) {
				InputStream reqBody = exch.getRequestBody();
				InputStreamReader in = new InputStreamReader(reqBody);
				RegisterRequest rr = json.JSONToObject(in, RegisterRequest.class);
				//Check to see if the request info is valid.
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
	
	/**Check that the request info is valid. Return true if it is, and false if there is a mistake.*/
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
