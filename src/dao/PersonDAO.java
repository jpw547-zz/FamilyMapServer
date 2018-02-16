package dao;

import java.sql.*;
import model.Person;

/**This class performs database operations for Person objects.
 * @author John Werner*/
public class PersonDAO {
	
	/**The general constructor for a PersonDAO object.*/
	public PersonDAO() {
		
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
	
	/**Adds a Person's information to the database.
	 * @param p		the Person object*/
	public void addPerson(Person p) {
		
	}
	
	/**Modifies an existing database entry for a Person.
	 * @param p		the Person object to be modified*/
	public void modifyPerson(Person p) {
		
	}
	
	/**Deletes an existing database entry for a Person.
	 * @param p		the Person object to be removed*/
	public void deletePerson(Person p) {
		
	}
	
	/**Deletes all Person information from the database.*/
	public void deleteAllPersons() {
		
	}
	
	/**Retrieves the information for a Person in the database.
	 * @param personID		the identifier for the Person to be returned
	 * @return				a Person object representing the information in the database.*/
	public Person getPerson(String personID) {
		return null;
	}
	
	/**Retrieves all information for all Persons in the database.
	 * @return 			an array of Person objects representing all the information in the Person table of the database.*/
	public Person[] getAllPersons() {
		return null;
	}
}
