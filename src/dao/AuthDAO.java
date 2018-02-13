package dao;

import java.sql.*;
import model.AuthToken;

/**This class performs database operations for AuthToken objects.
 * @author John Werner*/
public class AuthDAO {
	
	/**The general constructor for an AuthDAO object.*/
	public AuthDAO() {
		
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
	
	/**Adds an AuthToken's information to the database.
	 * @param a		the AuthToken object*/
	public void addAuthToken(AuthToken a) {
		
	}
	
	/**Modifies an existing database entry for an AuthToken.
	 * @param a		the AuthToken object to be modified*/
	public void modifyAuthToken(AuthToken a) {
		
	}
	
	/**Deletes an existing database entry for an AuthToken.
	 * @param a		the AuthToken object to be removed*/
	public void deleteAuthToken(AuthToken a) {
		
	}
	
	/**Deletes all AuthToken information from the database.*/
	public void deleteAllAuthTokens() {
		
	}
	
	/**Retrieves the information for an AuthToken in the database.
	 * @param authID		the identifier for the AuthToken to be returned
	 * @returns				an AuthToken object representing the information in the database.*/
	public AuthToken getAuthToken(String authID) {
		return null;
	}
	
	/**Retrieves all information for all AuthTokens in the database.
	 * @returns 			an array of AuthToken objects representing all the information in the AuthToken table of the database.*/
	public AuthToken[] getAllAuthTokens() {
		return null;
	}
}
