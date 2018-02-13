package dao;

import java.sql.*;
import model.AuthToken;

public class AuthDAO {
	private Connection c;
	
	public AuthDAO() {
		
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
	
	public void addAuthToken(AuthToken a) {
		
	}
	
	public void modifyAuthToken(AuthToken a) {
		
	}
	
	public void deleteAuthToken(AuthToken a) {
		
	}
	
	public void deleteAllAuthTokens() {
		
	}
	
	public AuthToken getAuthToken(String authID) {
		return null;
	}
	
	public AuthToken[] getAllAuthTokens() {
		return null;
	}
}
