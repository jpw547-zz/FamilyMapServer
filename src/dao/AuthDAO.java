package dao;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

import model.AuthToken;

/**This class performs database operations for AuthToken objects.
 * @author John Werner*/
public class AuthDAO {
	
	/**The general constructor for an AuthDAO object.*/
	public AuthDAO() {
		
	}
	
	/**The SQL Database Connection object.*/
	private Connection c;
	
	
	/**@return		the database Connection object*/
	public Connection getConnection() { return c; }
	
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
	
	/**Adds an AuthToken's information to the database.
	 * @param a		the AuthToken object
	 * @throws DatabaseException */
	public void addAuthToken(AuthToken a) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "INSERT INTO AuthTokens (tokenID, userName, personID) VALUES (?, ?, ?);";
				stmt = c.prepareStatement(sql);
				stmt.setString(1, a.getAuthTokenID());
				stmt.setString(2, a.getUserName());
				stmt.setString(3, a.getPersonId());
				if(stmt.executeUpdate() != 1) {
					throw new DatabaseException("Add AuthTokenfailed: Could not insert info");
				}
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException("Add AuthToken failed.", e);
		}
	}
	
	/**Deletes an existing database entry for an AuthToken.
	 * @param a		the AuthToken object to be removed
	 * @throws DatabaseException */
	public void deleteAuthToken(AuthToken a) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM AuthTokens WHERE tokenID = ?;";
				stmt = c.prepareStatement(sql);
				stmt.setString(1, a.getAuthTokenID());
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException("Delete AuthToken failed.", e);
		}
	}
	
	/**Deletes all AuthToken information from the database.
	 * @throws DatabaseException */
	public void deleteAllAuthTokens() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM AuthTokens;";
				stmt = c.prepareStatement(sql);
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException("Delete All AuthToken failed.", e);
		}
	}
	
	/**Retrieves the information for an AuthToken in the database.
	 * @param authID		the identifier for the AuthToken to be returned
	 * @return				an AuthToken object representing the information in the database.*/
	public AuthToken getAuthToken(String authID) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM AuthTokens WHERE tokenID = ?;";
				stmt = c.prepareStatement(sql);
				stmt.setString(1, authID);
				ResultSet rs = stmt.executeQuery();
				if(rs == null) { return null; }
				return new AuthToken(rs.getString("tokenID"), rs.getString("userName"), rs.getString("personID"));
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException("Get AuthToken failed.", e);
		}
	}
	
	/**Retrieves all information for all AuthTokens in the database.
	 * @return 			an array of AuthToken objects representing all the information in the AuthToken table of the database.
	 * @throws DatabaseException */
	public Set<AuthToken> getAllAuthTokens() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM AuthTokens;";
				stmt = c.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				if(rs == null) { return null; }
				Set<AuthToken> all = new TreeSet<AuthToken>(); 
				while(rs.next()) {
					String id = rs.getString("tokenID");
					String user = rs.getString("userName");
					String person = rs.getString("personID");
					all.add(new AuthToken(id, user, person));
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
			throw new DatabaseException("Get AuthToken failed.", e);
		}
	}
}
