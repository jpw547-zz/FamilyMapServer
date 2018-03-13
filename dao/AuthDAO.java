package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;

import model.AuthToken;

/**This class performs database operations for AuthToken objects.
 * @author John Werner*/
public class AuthDAO {	
	/**The general constructor for an AuthDAO object.
	 * @param c					the database Connection object*/
	public AuthDAO(Connection c) {
		setConnection(c);
	}
	
	/**The Logger object to log statements on the server log.*/
	private static Logger logger;
	static { logger = Logger.getLogger("familymaptest"); }
	
	/**A reference to the database Connection object*/
	private Connection c;
	
	
	
	/**Sets the connection for the AuthDAO object.
	 * @param c					the database Connection object*/
	public void setConnection(Connection c) { this.c = c; }

	/**@return					the database Connection object*/
	public Connection getConnection() { return c; }
	
	/**Adds an AuthToken's information to the database.
	 * @param a					the AuthToken object
	 * @throws 					DatabaseException */
	public void addAuthToken(AuthToken a) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "INSERT INTO AuthTokens (tokenID, userName, personID) VALUES (?, ?, ?);";
				stmt = c.prepareStatement(sql);
				
				//Fill statement with the AuthToken parameters.
				stmt.setString(1, a.getAuthTokenID());
				stmt.setString(2, a.getUserName());
				stmt.setString(3, a.getPersonID());
				
				//Execute the finalized statement.
				logger.log(Level.FINEST, "Adding AuthToken");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Add AuthToken failed. : %s", e.getLocalizedMessage()));
		}
	}
	
	/**Deletes an existing database entry for an AuthToken.
	 * @param a					the AuthToken object to be removed
	 * @throws 					DatabaseException */
	public void deleteAuthToken(AuthToken a) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM AuthTokens WHERE tokenID = ?;";
				stmt = c.prepareStatement(sql);
				
				//Fill statement with the AuthTokenID.
				stmt.setString(1, a.getAuthTokenID());
				
				//Execute the finalized statement.
				logger.log(Level.FINEST, "Deleting AuthToken");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Delete AuthToken failed. : %s", e.getLocalizedMessage()));
		}
	}
	
	/**Deletes all AuthToken information from the database.
	 * @throws 						DatabaseException */
	public void deleteAllAuthTokens() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM AuthTokens;";
				stmt = c.prepareStatement(sql);
				
				//No parameters to add to the statement, so proceed to execution.
				logger.log(Level.FINEST, "Deleting all AuthTokens.");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Delete All AuthTokens failed. : %s", e.getLocalizedMessage()));
		}
	}
	
	/**Retrieves the information for an AuthToken in the database.
	 * @param authID				the identifier for the AuthToken to be returned
	 * @return						an AuthToken object representing the information in the database.
	 * @throws DatabaseException 	if it is unable to get an AuthToken*/
	public AuthToken getAuthToken(String authID) throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM AuthTokens WHERE tokenID = ?;";
				stmt = c.prepareStatement(sql);
				
				//Fill statement with the given authID.
				stmt.setString(1, authID);
				
				//Execute the query and add the values to a new object to be returned.
				logger.log(Level.FINEST, "Getting AuthToken.");
				ResultSet rs = stmt.executeQuery();
				return new AuthToken(rs.getString("tokenID"), rs.getString("userName"), rs.getString("personID"));
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Get AuthToken failed. : %s", e.getLocalizedMessage()));
		}
	}
	
	/**Retrieves all information for all AuthTokens in the database.
	 * @return 						an array of AuthToken objects representing all the information in the AuthToken table of the database.
	 * @throws DatabaseException	if unable to get any AuthTokens */
	public AuthToken[] getAllAuthTokens() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM AuthTokens;";
				stmt = c.prepareStatement(sql);
				
				//No additional parameters to add, so we execute the query.
				logger.log(Level.FINEST, "Getting all AuthTokens.");
				ResultSet rs = stmt.executeQuery();
				
				//Iterate through the ResultSet and create new AuthTokens to be returned in the final Set.
				ArrayList<AuthToken> res = new ArrayList<AuthToken>();
				while(rs.next()) {
					String id = rs.getString("tokenID");
					String user = rs.getString("userName");
					String person = rs.getString("personID");
					res.add(new AuthToken(id, user, person));
				}
				AuthToken[] all = new AuthToken[res.size()];
				res.toArray(all);
				return all;
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Get All AuthTokens failed. : %s", e.getLocalizedMessage()));
		}
	}
}
