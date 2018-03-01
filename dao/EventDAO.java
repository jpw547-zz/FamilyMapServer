package dao;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Event;

/**This class performs database operations for Event objects.
 * @author John Werner*/
public class EventDAO {
	
//Constructors
	/**The general constructor for an EventDAO object.*/
	public EventDAO(Connection c) {
		setConnection(c);
	}
	
//Data members
	private static Logger logger;
	
	static {
        logger = Logger.getLogger("familymaptest");
    }
	
	private Connection c;
	
//Setters
	public void setConnection(Connection c) { this.c = c; }
	
//Getters
	/**@return				the database Connection object*/
	public Connection getConnection() { return c; }
	
//Remaining class methods	
	/**Adds an Event's information to the database.
	 * @param e				the Event object
	 * @throws				DatabaseException*/
	public void addEvent(Event e) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "INSERT INTO Events (eventID, personID, descendant, latitude, longitude, country, city, eventType, year) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with the Event parameters.
				stmt.setString(1, e.getEventID());
				stmt.setString(2, e.getPersonID());
				stmt.setString(3, e.getDescendant());
				stmt.setDouble(4, e.getLatitude());
				stmt.setDouble(5, e.getLongitude());
				stmt.setString(6, e.getCountry());
				stmt.setString(7, e.getCity());
				stmt.setString(8, e.getEventType());
				stmt.setString(9, e.getYear());
				
				//Execute the finalized statement.
				logger.log(Level.FINE, "Adding Event");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException(String.format("Add Event failed. : %s ::UD", err.getLocalizedMessage()));
		}
	}
	
	/**Modifies an existing database entry for an Event.
	 * @param e				the Event object to be modified
	 * @throws 				DatabaseException */
	public void modifyEvent(Event e) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "UPDATE Events SET personID=?, descendant=?, latitude=?, longitude=?, country=?, city=?, eventType=?, year=? WHERE eventID=?;";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with the Event parameters.
				stmt.setString(1, e.getPersonID());
				stmt.setString(2, e.getDescendant());
				stmt.setDouble(3, e.getLatitude());
				stmt.setDouble(4, e.getLongitude());
				stmt.setString(5, e.getCountry());
				stmt.setString(6, e.getCity());
				stmt.setString(7, e.getEventType());
				stmt.setString(8, e.getYear());
				stmt.setString(9, e.getEventID());
				
				//Execute the finalized statement.
				logger.log(Level.FINE, "Modifying Event");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException(String.format("Modify Event failed. : %s ::UD", err.getLocalizedMessage()));
		}
	}
	
	/**Deletes an existing database entry for an Event.
	 * @param e				the Event object to be removed
	 * @throws 				DatabaseException */
	public void deleteEvent(Event e) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM Events WHERE eventID = ?;";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with eventID.
				stmt.setString(1, e.getEventID());
				
				//Execute the finalized statement.
				logger.log(Level.FINE, "Deleting Event");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException(String.format("Delete Event failed. : %s ::UD", err.getLocalizedMessage()));
		}
	}
	
	/**Deletes all Event information from the database.
	 * @throws 				DatabaseException */
	public void deleteAllEvents() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM Events;";
				stmt = c.prepareStatement(sql);
				
				//No extra parameters to add to the statement, so proceed to execution.
				logger.log(Level.FINE, "Deleting all Events");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			System.out.println("Delete All Events failed.");
			throw new DatabaseException(String.format("Delete all Events failed. : %s ::UD", err.getLocalizedMessage()));
		}
	}
	
	/**Retrieves the information for an Event in the database.
	 * @param eventID		the identifier for the Event to be returned
	 * @return				an Event object representing the information in the database.
	 * @throws 				DatabaseException */
	public Event getEvent(String eventID) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM Events WHERE eventID = ?;";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with the given eventID.
				stmt.setString(1, eventID);
				
				//Execute the query, and construct the Event from the information in the ResultSet.
				logger.log(Level.FINE, "Getting Event");
				ResultSet rs = stmt.executeQuery();
				return new Event(
						rs.getString("eventID"), 
						rs.getString("personID"), 
						rs.getString("descendant"), 
						rs.getDouble("latitude"), 
						rs.getDouble("longitude"), 
						rs.getString("country"),
						rs.getString("city"),
						rs.getString("eventType"),
						rs.getString("year"));
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException(String.format("Get Event failed. : %s ::UD", err.getLocalizedMessage()));
		}
	}
	
	/**Retrieves all information for all Events in the database.
	 * @return 				an array of Event objects representing all the information in the Event table of the database.
	 * @throws 				DatabaseException */
	public Set<Event> getAllEvents(String descendant) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM Events WHERE descendant=?;";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with the descendant's userNamerr.
				stmt.setString(1, descendant);
				
				//Execute the finalized query.
				logger.log(Level.FINE, "Getting all Events");
				ResultSet rs = stmt.executeQuery();
				
				//Iterate over the ResultSet to construct Event objects and add them to the Set to be returned.
				Set<Event> all = new TreeSet<Event>(); 
				while(rs.next()) {
					String eID = rs.getString("eventID"); 
					String pID = rs.getString("personID"); 
					String desc = rs.getString("descendant"); 
					double lati = rs.getDouble("latitude");
					double longi = rs.getDouble("longitude"); 
					String country = rs.getString("country");
					String city = rs.getString("city");
					String eT = rs.getString("eventType");
					String year = rs.getString("year");
					all.add(new Event(eID, pID, desc, lati, longi, country, city, eT, year));
				}
				return all;
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException(String.format("Get All Events failed. : %s ::UD", err.getLocalizedMessage()));
		}
	}
}
