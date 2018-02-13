package dao;

import java.sql.*;
import model.Event;

/**This class performs database operations for Event objects.
 * @author John Werner*/
public class EventDAO {
	
	/**The general constructor for an EventDAO object.*/
	public EventDAO() {
		
	}
	
	/**The SQL Database Connection object.*/
	private Connection c;
	
	/**@returns		the database Connection object*/
	public Connection getConnection() { 
		return c;
	}
	
	/**Establishes a connection to the SQL database.*/
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
	
	/**Adds an Event's information to the database.
	 * @param e		the Event object*/
	public void addEvent(Event e) {
		
	}
	
	/**Modifies an existing database entry for an Event.
	 * @param e		the Event object to be modified*/
	public void modifyEvent(Event e) {
		
	}
	
	/**Deletes an existing database entry for an Event.
	 * @param e		the Event object to be removed*/
	public void deleteEvent(Event e) {
		
	}
	
	/**Deletes all Event information from the database.*/
	public void deleteAllEvents() {
		
	}
	
	/**Retrieves the information for an Event in the database.
	 * @param eventID		the identifier for the Event to be returned
	 * @returns				an Event object representing the information in the database.*/
	public Event getEvent(String eventID) {
		return null;
	}
	
	/**Retrieves all information for all Events in the database.
	 * @returns 			an array of Event objects representing all the information in the Event table of the database.*/
	public Event[] getAllEvents(String personID) {
		return null;
	}
}
