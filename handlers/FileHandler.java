package handlers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;

import com.sun.net.httpserver.*;

public class FileHandler implements HttpHandler{

	private final static String webFile = "web/index.html";
	
	public void handle(HttpExchange exch) throws IOException {
		
		File file = new File(webFile);
		
		exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		OutputStream out = exch.getResponseBody();
		Files.copy(file.toPath(), out);
		
		out.flush();
		out.close();
	}
}
