package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

import model.User;

/**This class performs database operations for User objects.
 * @author John Werner*/
public class UserDAO {	
	/**The general constructor for a UserDAO object.
	 * @param c			the database Connection object*/
	public UserDAO(Connection c) {
		setConnection(c);
	}
	
	/**The Logger object to log statements on the server log.*/
	private static Logger logger;
	static { logger = Logger.getLogger("familymaptest"); }
	
	/**A reference to the database Connection object*/
	private Connection c;
	
	
	
	/**Sets the connection for the UserDAO object.
	 * @param c			the database Connection object*/
	public void setConnection(Connection c) { this.c = c; }

	/**@return			the database Connection object*/
	public Connection getConnection() { return c; }
	
	/**Adds a User's information to the database.
	 * @param u			the User object
	 * @throws 			DatabaseException */
	public void addUser(User u) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "INSERT INTO Users (userName, password, email, firstName, lastName, gender, personID) VALUES (?, ?, ?, ?, ?, ?, ?);";
				stmt = c.prepareStatement(sql);
				
				//Fill the statement with the User's parameters.
				stmt.setString(1, u.getUserName());
				stmt.setString(2, u.getPassword());
				stmt.setString(3, u.getEmail());
				stmt.setString(4, u.getFirstName());
				stmt.setString(5, u.getLastName());
				stmt.setString(6, String.valueOf(u.getGender()));
				stmt.setString(7, u.getPersonID());
				
				//Execute the finalized statement.
				logger.log(Level.FINEST, "Adding User");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			if(e.getLocalizedMessage().contains("not unique")) {
				throw new DatabaseException("Username already registered in the database.");
			}
			throw new DatabaseException(String.format("Add User failed. : %s", e.getLocalizedMessage()));
		}
	}
	
	/**Modifies an existing database entry for a User.
	 * @param u			the User object to be modified
	 * @throws 			DatabaseException */
	public void modifyUser(User u) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "UPDATE Users SET password=?, email=?, firstName=?, lastName=?, gender=?, personID=? WHERE userName=?;";
				stmt = c.prepareStatement(sql);
				
				//Fill statement with the User's parameters.
				stmt.setString(1, u.getPassword());
				stmt.setString(2, u.getEmail());
				stmt.setString(3, u.getFirstName());
				stmt.setString(4, u.getLastName());
				stmt.setString(5, String.valueOf(u.getGender()));
				stmt.setString(6, u.getPersonID());
				stmt.setString(7, u.getUserName());
				
				//Execute the finalized statement.
				logger.log(Level.FINEST, "Modifying User");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Modify User failed. : %s", e.getLocalizedMessage()));
		}
	}
	
	/**Deletes an existing database entry for a User.
	 * @param u			the User object to be removed
	 * @throws 			DatabaseException */
	public void deleteUser(User u) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM Users WHERE userName=?;";
				stmt = c.prepareStatement(sql);
				
				//Fill statement with the User's userName.
				stmt.setString(1, u.getUserName());
				
				//Execute the finalized statement.
				logger.log(Level.FINEST, "Deleting User");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Delete User failed. : %s", e.getLocalizedMessage()));
		}
		
	}
	
	/**Deletes all User information from the database.
	 * @throws 			DatabaseException */
	public void deleteAllUsers() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM Users;";
				stmt = c.prepareStatement(sql);
				
				//No extra parameters to add to the statement, so proceed to execution.
				logger.log(Level.FINEST, "Deleting all Users");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Delete all Users failed. : %s", e.getLocalizedMessage()));
		}
	}
	
	/**Retrieves the information for a User in the database.
	 * @param username	the identifier for the User to be returned
	 * @return			a User object representing the information in the database.
	 * @throws 			DatabaseException */
	public User getUser(String userName) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM Users WHERE userName=?;";
				stmt = c.prepareStatement(sql);
				
				//Fill statement with the given userName.
				stmt.setString(1, userName);
				
				//Execute the finalized query, and construct a User object using the data from the ResultSet.
				logger.log(Level.FINEST, "Getting User");
				ResultSet rs = stmt.executeQuery();
				if(!rs.next()) { throw new DatabaseException("Get User failed. : User not found."); }
				return new User(
						rs.getString("userName"), 
						rs.getString("password"), 
						rs.getString("email"), 
						rs.getString("firstName"),
						rs.getString("lastName"),
						rs.getString("gender").charAt(0),
						rs.getString("personID")
						);
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Get User failed. : %s", e.getLocalizedMessage()));
		}
	}
	
	/**Retrieves all information for all Persons in the database.
	 * @return 			an array of User objects representing all the information in the User table of the database.
	 * @throws 			DatabaseException */
	public User[] getAllUsers() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM Users;";
				stmt = c.prepareStatement(sql);
				
				//No extra parameters to add to the statement, so proceed to execution.
				logger.log(Level.FINEST, "Getting all Users");
				ResultSet rs = stmt.executeQuery();
				
				//Iterate through the ResultSet and use the data to build User objects to add to the Set.
				ArrayList<User> res = new ArrayList<User>();
				while(rs.next()) {
					String u = rs.getString("userName");
					String p = rs.getString("password");
					String e = rs.getString("email");
					String f = rs.getString("firstName");
					String l = rs.getString("lastName");
					char g = rs.getString("gender").charAt(0);
					String id = rs.getString("personID");
					res.add(new User(u, p, e, f, l, g, id));
				}
				User[] all = new User[res.size()];
				res.toArray(all);
				return all;
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch(SQLException e) {
			throw new DatabaseException(String.format("Get All Users failed. : %s", e.getLocalizedMessage()));
		}
	}
}
