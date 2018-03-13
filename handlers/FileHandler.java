package handlers;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.file.Files;

import com.sun.net.httpserver.*;

public class FileHandler implements HttpHandler{
	/**The root directory for the website files.*/
	private final static String webFile = "web";
	
	/**The handler for serving up the website interface for the server.*/
	public void handle(HttpExchange exch) throws IOException {
		String url = exch.getRequestURI().toString();
		String fileName = webFile;
		if(url.equals("/")) {
			fileName = fileName + "/index.html";
		}
		else {
			fileName += url;
		}
		File file = new File(fileName);
		
		exch.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		OutputStream out = exch.getResponseBody();
		Files.copy(file.toPath(), out);
		
		out.flush();
		out.close();
	}
}
