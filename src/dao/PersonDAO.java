package dao;

import java.sql.*;
import model.Person;

public class PersonDAO {
	private Connection c;
	
	public PersonDAO() {
		
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
	
	public void addPerson(model.Person p) {
		
	}
	
	public void modifyPerson(model.Person p) {
		
	}
	
	public void deletePerson(model.Person p) {
		
	}
	
	public void deleteAllPersons() {
		
	}
}
