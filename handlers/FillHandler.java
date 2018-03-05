package handlers;

import handlers.json.JSONConverter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import requests.FillRequest;
import results.Result;
import services.FillService;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class FillHandler implements HttpHandler {

	private Result error;
	
	public void handle(HttpExchange exch) throws IOException {
		boolean success = false;
		JSONConverter json = new JSONConverter();
		
		try {
			if(exch.getRequestMethod().toUpperCase().equals("POST")) {
				FillRequest fr;
				String[] params = exch.getRequestURI().toString().split("/");
				if(params.length == 4) {
					fr = new FillRequest(params[2], Integer.parseInt(params[3])); 
				}
				else {
					fr = new FillRequest(params[2], 0);
				}
				if(validateRequestInfo(fr)) {
					Result res = new FillService().fill(fr);
					exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
					OutputStream respBody = exch.getResponseBody();
					OutputStreamWriter out = new OutputStreamWriter(respBody);
					out.write(json.ObjectToJSON(res));
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
	
	private boolean validateRequestInfo(FillRequest fr) {
		if(fr.getUsername().isEmpty()) { error = new Result("Username empty"); return false; }
		if(fr.getGenerations() < 0) { error = new Result("Invalid generations"); return false; }

		//At this point everything checks out.
		return true;
	}
}
