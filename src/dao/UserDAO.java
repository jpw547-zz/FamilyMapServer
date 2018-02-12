package dao;

import java.sql.*;
import model.User;

public class UserDAO {
	private Connection c;
	
	public UserDAO() {
		
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
	
	public void addUser(model.User u) {
		
	}
	
	public void modifyUser(model.User u) {
		
	}
	
	public void deleteUser(model.User u) {
		
	}
	
	public void deleteAllUsers() {
		
	}
}
