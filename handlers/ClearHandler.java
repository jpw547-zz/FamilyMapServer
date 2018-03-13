package handlers;

import handlers.json.JSONConverter;

import java.io.*;
import java.net.HttpURLConnection;

import results.Result;
import services.ClearService;

import com.sun.net.httpserver.*;

public class ClearHandler implements HttpHandler {
	/**The handler function to call for the database to be cleared.*/
	public void handle(HttpExchange exch) throws IOException {
		try {
			JSONConverter json = new JSONConverter();
			
			Result result = new ClearService().clear();
			
			exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			OutputStream respBody = exch.getResponseBody();
			OutputStreamWriter out = new OutputStreamWriter(respBody);
			out.write(json.ObjectToJSON(result));
			out.flush();
			respBody.close();
		} catch (IOException io) {
			exch.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
			exch.getResponseBody().close();
			io.printStackTrace();
		}
	}
}
