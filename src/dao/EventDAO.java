package dao;

import java.sql.*;
import model.Event;

public class EventDAO {
	private Connection c;
	
	public EventDAO() {
		
	}
	
	public Connection getConnection() { 
		return c;
	}
	
	public void setConnection() {
		try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:fmdb.db");
	         c.setAutoCommit(false);
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	}
	
	public void addEvent(model.Event e) {
		
	}
	
	public void modifyEvent(model.Event e) {
		
	}
	
	public void deleteEvent(model.Event e) {
		
	}
	
	public void deleteAllEvents() {
		
	}
}
