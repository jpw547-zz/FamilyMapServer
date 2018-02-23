package dao;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

import model.Person;

/**This class performs database operations for Person objects.
 * @author John Werner*/
public class PersonDAO {
	
//Constructors
	/**The general constructor for a PersonDAO object.*/
	public PersonDAO() {}

//Data members
	/**The SQL Database Connection object.*/
	private Connection c;
	
//Setters
	/**Establishes a connection to the SQL database.*/
	public void setConnection() {
		try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:fmdb.db");
	         c.setAutoCommit(false);
	      } catch (Exception e) {
	         System.err.println(e.getClass().getName() + ": " + e.getMessage());
	      }
	      //System.out.println("Opened database successfully");
	}
	
//Getters
	/**@return				the database Connection object*/
	public Connection getConnection() { return c; }
	
//Remaining class methods
	/**Closes the connection to the database.
	 * @param commit		true to commit changes, false to rollback.
	 * @throws 				DatabaseException */
	public void closeConnection(boolean commit) throws DatabaseException {
		try {
			if(commit) { c.commit(); }
			if(!commit) { c.rollback(); }
            c.close();
            c = null;
        } catch (SQLException e) {
            throw new DatabaseException("closeConnection failed", e);
        }
	}
	
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
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException("Add Person failed.", err);
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
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException("Modify Person failed.", err);
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
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException("Delete Person failed.", err);
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
				stmt.executeUpdate();
			} 
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException("Delete All Persons failed.", err);
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
				ResultSet rs = stmt.executeQuery();
				if(rs == null) { return null; }
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
			throw new DatabaseException("Get Person failed.", e);
		}
	}
	
	/**Retrieves all information for all Persons in the database.
	 * @return 				an array of Person objects representing all the information in the Person table of the database.
	 * @throws 				DatabaseException */
	public Set<Person> getAllPersons(String descendant) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM Persons WHERE descendant=?;";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with the descendant's userName.
				stmt.setString(1, descendant);
				
				//Execute the finalized query.
				ResultSet rs = stmt.executeQuery();
				if(rs == null) { return null; }
				
				//Iterate over the ResultSet and use the data to construct Person objects and add them to the Set.
				Set<Person> all = new TreeSet<Person>(); 
				while(rs.next()) {
					String p = rs.getString("personID");
					String f = rs.getString("firstName"); 
					String l = rs.getString("lastName");
					char c = rs.getString("gender").charAt(0); 
					String d = rs.getString("descendant");
					String father = rs.getString("father");
					String mother = rs.getString("mother");
					String spouse = rs.getString("spouse");
					all.add(new Person(p, f, l, c, d, father, mother, spouse));
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
			throw new DatabaseException("Get All Persons failed.", e);
		}
	}
}
