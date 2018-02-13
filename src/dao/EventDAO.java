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
	
	public void addEvent(Event e) {
		
	}
	
	public void modifyEvent(Event e) {
		
	}
	
	public void deleteEvent(Event e) {
		
	}
	
	public void deleteAllEvents() {
		
	}
	
	public Event getEvent(String eventID) {
		return null;
	}
	
	public Event[] getAllEvents(String personID) {
		return null;
	}
}
