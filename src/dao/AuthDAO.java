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
	
	public void addAuthToken(model.AuthToken a) {
		
	}
	
	public void modifyAuthToken(model.AuthToken a) {
		
	}
	
	public void deleteAuthToken(model.AuthToken a) {
		
	}
	
	public void deleteAllAuthTokens() {
		
	}
}
