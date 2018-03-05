package handlers;

import handlers.json.JSONConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import requests.LoadRequest;
import results.Result;
import services.LoadService;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoadHandler implements HttpHandler {

	private Result error;
	
	public void handle(HttpExchange exch) throws IOException {
		boolean success = false;
		JSONConverter json = new JSONConverter();
		
		try {
			if(exch.getRequestMethod().toUpperCase().equals("POST")) {
				InputStream reqBody = exch.getRequestBody();
				InputStreamReader in = new InputStreamReader(reqBody);
				LoadRequest lr = json.JSONToObject(in, LoadRequest.class);
				if(validateRequestInfo(lr)) {
					Result ar = new LoadService().load(lr);
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
	
	private boolean validateRequestInfo(LoadRequest lr) {
		//if(lr.getUserList() == null) { error = new Result("No User array included in request"); return false; }
		if(lr.getPersonList() == null) { error = new Result("No Person array included in request"); return false; }
		if(lr.getEventList() == null) { error = new Result("No Event array included in request"); return false; }
		
		//At this point everything checks out.
		return true;
	}
}
