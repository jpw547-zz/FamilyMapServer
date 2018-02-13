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
	
	public void addUser(User u) {
		
	}
	
	public void modifyUser(User u) {
		
	}
	
	public void deleteUser(User u) {
		
	}
	
	public void deleteAllUsers() {
		
	}
	
	public User getUser(String username) {
		return null;
	}
	
	public User[] getAllUsers() {
		return null;
	}
}
