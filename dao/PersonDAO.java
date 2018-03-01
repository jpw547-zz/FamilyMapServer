package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

import model.Person;

/**This class performs database operations for Person objects.
 * @author John Werner*/
public class PersonDAO {
	
//Constructors
	/**The general constructor for a PersonDAO object.*/
	public PersonDAO(Connection c) {
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
	/**Adds a Person's information to the database.
	 * @param p				the Person object
	 * @throws 				DatabaseException */
	public void addPerson(Person p) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "INSERT INTO Persons (personID, firstName, lastName, gender, descendant, father, mother, spouse) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with the Person parameters.
				stmt.setString(1, p.getPersonID());
				stmt.setString(2, p.getFirstName());
				stmt.setString(3, p.getLastName());
				stmt.setString(4, String.valueOf(p.getGender()));
				stmt.setString(5, p.getDescendant());
				stmt.setString(6, p.getFather());
				stmt.setString(7, p.getMother());
				stmt.setString(8, p.getSpouse());
				
				//Execute the finalized statement.
				logger.log(Level.FINE, "Adding Person");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Add Person failed. : %s ::UD", e.getLocalizedMessage()));
		}
	}
	
	/**Modifies an existing database entry for a Person.
	 * @param p				the Person object to be modified
	 * @throws 				DatabaseException */
	public void modifyPerson(Person p) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "UPDATE Persons SET firstName=?, lastName=?, gender=?, descendant=?, father=?, mother=?, spouse=? WHERE personID=?;";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with the Person parameters.
				stmt.setString(1, p.getFirstName());
				stmt.setString(2, p.getLastName());
				stmt.setString(3, String.valueOf(p.getGender()));
				stmt.setString(4, p.getDescendant());
				stmt.setString(5, p.getFather());
				stmt.setString(6, p.getMother());
				stmt.setString(7, p.getSpouse());
				stmt.setString(8, p.getPersonID());
				
				//Execute the finalized statement.
				logger.log(Level.FINE, "Modifying Person");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Modify Person failed. : %s ::UD", e.getLocalizedMessage()));
		}
	}
	
	/**Deletes an existing database entry for a Person.
	 * @param p				the Person object to be removed
	 * @throws 				DatabaseException */
	public void deletePerson(Person p) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM Persons WHERE personID = ?;";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with the Person's personID.
				stmt.setString(1, p.getPersonID());
				
				//Execute the finalized statement.
				logger.log(Level.FINE, "Deleting Person");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Delete Person failed. : %s ::UD", e.getLocalizedMessage()));
		}
	}
	
	/**Deletes all Person information from the database.
	 * @throws 				DatabaseException */
	public void deleteAllPersons() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM Persons;";
				stmt = c.prepareStatement(sql);
				
				//No extra parameters to add to the statement, so proceed to execution.
				logger.log(Level.FINE, "Deleting all Persons");
				stmt.executeUpdate();
			} 
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Delete all Persons failed. : %s ::UD", e.getLocalizedMessage()));
		}
	}
	
	/**Retrieves the information for a Person in the database.
	 * @param personID		the identifier for the Person to be returned
	 * @return				a Person object representing the information in the database.
	 * @throws 				DatabaseException */
	public Person getPerson(String personID) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM Persons WHERE personID = ?;";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with the given personID.
				stmt.setString(1, personID);
				
				//Execute the query, and construct a new Person object from the data in the ResultSet.
				logger.log(Level.FINE, "Getting Person");
				ResultSet rs = stmt.executeQuery();
				return new Person( 
						rs.getString("personID"), 
						rs.getString("firstName"), 
						rs.getString("lastName"), 
						rs.getString("gender").charAt(0), 
						rs.getString("descendant"),
						rs.getString("father"),
						rs.getString("mother"),
						rs.getString("spouse"));
			} 
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Get Person failed. : %s ::UD", e.getLocalizedMessage()));
		}
	}
	
	/**Retrieves all information for all Persons in the database.
	 * @return 				an array of Person objects representing all the information in the Person table of the database.
	 * @throws 				DatabaseException */
	public Person[] getAllPersons(String descendant) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM Persons WHERE descendant=?;";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with the descendant's userName.
				stmt.setString(1, descendant);
				
				//Execute the finalized query.
				logger.log(Level.FINE, "Getting all Persons");
				ResultSet rs = stmt.executeQuery();
				
				//Iterate over the ResultSet and use the data to construct Person objects and add them to the Set.
				
				ArrayList<Person> res = new ArrayList<Person>();
				
				int rowCount = 0;
				while(rs.next()) {
					String p = rs.getString("personID");
					String f = rs.getString("firstName"); 
					String l = rs.getString("lastName");
					char c = rs.getString("gender").charAt(0); 
					String d = rs.getString("descendant");
					String father = rs.getString("father");
					String mother = rs.getString("mother");
					String spouse = rs.getString("spouse");
					res.add(new Person(p, f, l, c, d, father, mother, spouse));
					rowCount++;
				}
				Person[] all = new Person[res.size()];
				for(int i = 0; i < res.size(); i++) {
					all[i] = res.get(i);
				}
				return all;
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Get All Persons failed. : %s ::UD", e.getLocalizedMessage()));
		}
	}
}
