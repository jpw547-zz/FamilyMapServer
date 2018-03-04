package handlers;

import handlers.json.JSONConverter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import results.Result;
import services.ClearService;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ClearHandler implements HttpHandler {

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
