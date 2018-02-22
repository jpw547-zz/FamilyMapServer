package dao;

import java.sql.*;

import model.User;

/**This class performs database operations for User objects.
 * @author John Werner*/
public class UserDAO {
	
	/**The general constructor for a UserDAO object.*/
	public UserDAO() {
		
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
	      //System.out.println("Opened database successfully");
	}
	
	/**Closes the connection to the database.
	 * @param commit	true to commit changes, false to rollback.
	 * @throws DatabaseException */
	public void closeConnection(boolean commit) throws DatabaseException {
		try {
			if(commit) { c.commit(); }
			if(!commit) { c.rollback(); }
            c.close();
            c = null;
        }
        catch (SQLException e) {
            throw new DatabaseException("closeConnection failed", e);
        }
	}
	
	/**Adds a User's information to the database.
	 * @param u		the User object
	 * @throws DatabaseException */
	public void addUser(User u) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "INSERT INTO Users (userName, password, email, firstName, lastName, gender) VALUES (?, ?, ?, ?, ?, ?);";
				stmt = c.prepareStatement(sql);
				stmt.setString(1, u.getUserName());
				stmt.setString(2, u.getPassword());
				stmt.setString(3, u.getEmail());
				stmt.setString(4, u.getFirstName());
				stmt.setString(5, u.getLastName());
				stmt.setString(6, String.valueOf(u.getGender()));
				if(stmt.executeUpdate() != 1) {
					throw new DatabaseException("Add User failed: Could not insert info");
				}
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException("Add User failed.", e);
		}
	}
	
	/**Modifies an existing database entry for a User.
	 * @param u		the User object to be modified
	 * @throws DatabaseException */
	public void modifyUser(User u) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "UPDATE Users SET password=?, email=?, firstName=?, lastName=?, gender=? WHERE userName=?;";
				stmt = c.prepareStatement(sql);
				stmt.setString(1, u.getPassword());
				stmt.setString(2, u.getEmail());
				stmt.setString(3, u.getFirstName());
				stmt.setString(4, u.getLastName());
				stmt.setString(5, String.valueOf(u.getGender()));
				stmt.setString(6, u.getUserName());
				if(stmt.executeUpdate() != 1) {
					throw new DatabaseException("Modify User failed: Could not update info");
				}
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException("Modify User failed.", err);
		}
	}
	
	/**Deletes an existing database entry for a User.
	 * @param u		the User object to be removed
	 * @throws DatabaseException */
	public void deleteUser(User u) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM Users WHERE userName=?;";
				stmt = c.prepareStatement(sql);
				stmt.setString(1, u.getUserName());
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException("Delete User failed.", err);
		}
		
	}
	
	/**Deletes all User information from the database.
	 * @throws DatabaseException */
	public void deleteAllUsers() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM Users;";
				stmt = c.prepareStatement(sql);
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException err) {
			throw new DatabaseException("Delete All Users failed.", err);
		}
	}
	
	/**Retrieves the information for a User in the database.
	 * @param username		the identifier for the User to be returned
	 * @return				a User object representing the information in the database.
	 * @throws DatabaseException */
	public User getUser(String username) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM Users WHERE userName = ?;";
				stmt = c.prepareStatement(sql);
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				if(rs == null) { return null; }
				return new User(
						rs.getString("userName"), 
						rs.getString("password"), 
						rs.getString("email"), 
						rs.getString("firstName"),
						rs.getString("lastName"),
						rs.getString("gender").charAt(0)
						);
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException("Get User failed.", e);
		}
	}
	
	/**Retrieves all information for all Persons in the database.
	 * @return 			an array of User objects representing all the information in the User table of the database.*/
	public User[] getAllUsers() {
		return null;
	}
}
