package handlers;

import handlers.json.JSONConverter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import requests.EventRequest;
import results.EventResult;
import results.Result;
import services.EventService;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EventHandler implements HttpHandler {

private Result error;
	
	public void handle(HttpExchange exch) throws IOException {
		boolean success = false;
		JSONConverter json = new JSONConverter();
		
		try {
			if(exch.getRequestMethod().toUpperCase().equals("GET")) {
				Headers reqHeaders = exch.getRequestHeaders();
				if (reqHeaders.containsKey("Authorization")) {
					String authToken = reqHeaders.getFirst("Authorization");
					EventRequest er;
					String[] params = exch.getRequestURI().toString().split("/");
					if(params.length == 3) {
						er = new EventRequest(authToken, params[2]);
						if(validateRequestInfo(er)) {
							EventResult res = new EventService().getEvent(er);
							exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
							OutputStream respBody = exch.getResponseBody();
							OutputStreamWriter out = new OutputStreamWriter(respBody);
							out.write(json.ObjectToJSON(res));
							out.flush();
							respBody.close();
							success = true;
						}
					}
					else {
						er = new EventRequest(authToken, "");
						if(validateRequestInfo(er)) {
							EventResult res = new EventService().getAll(er);
							exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
							OutputStream respBody = exch.getResponseBody();
							OutputStreamWriter out = new OutputStreamWriter(respBody);
							out.write(json.ObjectToJSON(res));
							out.flush();
							respBody.close();
							success = true;
						}
					}
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
	
	private boolean validateRequestInfo(EventRequest er) {
		if(er.getAuthTokenID().isEmpty()) { error = new Result("AuthToken empty"); return false; }

		//At this point everything checks out.
		return true;
	}
}
