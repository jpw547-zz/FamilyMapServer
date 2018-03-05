package server;

import handlers.*;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Server {
	private static final int MAX_WAITING_CONNECTIONS = 12;
	private HttpServer server;
	
	private void run(String portNumber) {
		System.out.println("Initializing server");
		
		try {
			server = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNumber)), MAX_WAITING_CONNECTIONS);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		server.setExecutor(null);
		
		System.out.println("Creating contexts");
		// *** HANDLERS ***
		// /user/register
		server.createContext("/user/register", new RegisterHandler());
		// /user/login
		server.createContext("/user/login", new LoginHandler());
		// /clear
		server.createContext("/clear", new ClearHandler());
		// /fill/[username]/{generations}
		server.createContext("/fill", new FillHandler());
		// /load
		server.createContext("/load", new LoadHandler());
		// /person OR /person/[personID]
		server.createContext("/person", new PersonHandler());
		// /event OR /event/[eventID]
		server.createContext("/event", new EventHandler());
		// / **Default file handler**
		server.createContext("/", new FileHandler());
		
		server.start();
		System.out.println("Server started");
	}
	
	public static void main(String[] args) {
		if(args.length != 1) { return; }
		String portNumber = args[0];
		new Server().run(portNumber);
	}
}
