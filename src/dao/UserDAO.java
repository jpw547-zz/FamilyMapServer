package dao;

import java.sql.*;
import model.User;

/**This class performs database operations for User objects.
 * @author John Werner*/
public class UserDAO {
	
	/**The general constructor for a UserDAO object.*/
	public UserDAO() {
		
	}
	
	/**The SQL Database Connection object.*/
	private Connection c;
	
	/**@return		the database Connection object*/
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
	
	/**Adds a User's information to the database.
	 * @param u		the User object*/
	public void addUser(User u) {
		
	}
	
	/**Modifies an existing database entry for a User.
	 * @param u		the User object to be modified*/
	public void modifyUser(User u) {
		
	}
	
	/**Deletes an existing database entry for a User.
	 * @param u		the User object to be removed*/
	public void deleteUser(User u) {
		
	}
	
	/**Deletes all User information from the database.*/
	public void deleteAllUsers() {
		
	}
	
	/**Retrieves the information for a User in the database.
	 * @param username		the identifier for the User to be returned
	 * @return				a User object representing the information in the database.*/
	public User getUser(String username) {
		return null;
	}
	
	/**Retrieves all information for all Persons in the database.
	 * @return 			an array of User objects representing all the information in the User table of the database.*/
	public User[] getAllUsers() {
		return null;
	}
}
