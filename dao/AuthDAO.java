package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.AuthToken;

/**This class performs database operations for AuthToken objects.
 * @author John Werner*/
public class AuthDAO {
	
//Constructors
	/**The general constructor for an AuthDAO object.*/
	public AuthDAO(Connection c) {
		setConnection(c);
	}
	
//Data members
	private static Logger logger;
	static { logger = Logger.getLogger("familymaptest"); }
	
	private Connection c;
	
//Setters
	public void setConnection(Connection c) { this.c = c; }
//Getters
	/**@return				the database Connection object*/
	public Connection getConnection() { return c; }
	
//Remaining class methods
	/**Adds an AuthToken's information to the database.
	 * @param a			the AuthToken object
	 * @throws 			DatabaseException */
	public void addAuthToken(AuthToken a) throws DatabaseException {
		//Check parameters before continuing.
		assert a != null : "Null AuthToken. ::AD::Add";
		
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
				logger.log(Level.FINE, "Adding AuthToken");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Add AuthToken failed. : %s ::AD", e.getLocalizedMessage()));
		}
	}
	
	/**Deletes an existing database entry for an AuthToken.
	 * @param a			the AuthToken object to be removed
	 * @throws 			DatabaseException */
	public void deleteAuthToken(AuthToken a) throws DatabaseException {
		//Check parameters before continuing.
		assert a != null : "Null AuthToken. ::AD::Delete";
				
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM AuthTokens WHERE tokenID = ?;";
				stmt = c.prepareStatement(sql);
				
				//Fill statement with the AuthTokenID.
				stmt.setString(1, a.getAuthTokenID());
				
				//Execute the finalized statement.
				logger.log(Level.FINE, "Deleting AuthToken");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Delete AuthToken failed. : %s ::AD", e.getLocalizedMessage()));
		}
	}
	
	/**Deletes all AuthToken information from the database.
	 * @throws 			DatabaseException */
	public void deleteAllAuthTokens() throws DatabaseException {
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "DELETE FROM AuthTokens;";
				stmt = c.prepareStatement(sql);
				
				//No parameters to add to the statement, so proceed to execution.
				logger.log(Level.FINE, "Deleting all AuthTokens.");
				stmt.executeUpdate();
			}
			finally {
				if(stmt != null) {
					stmt.close();
					stmt = null;
				}
			}
		} catch (SQLException e) {
			throw new DatabaseException(String.format("Delete All AuthTokens failed. : %s ::AD", e.getLocalizedMessage()));
		}
	}
	
	/**Retrieves the information for an AuthToken in the database.
	 * @param authID				the identifier for the AuthToken to be returned
	 * @return						an AuthToken object representing the information in the database.
	 * @throws DatabaseException 	if it is unable to get an AuthToken*/
	public AuthToken getAuthToken(String authID) throws DatabaseException {
		//Check parameters before continuing.
		assert !authID.equals("") : "Empty authID. ::AD::Get";
		
		PreparedStatement stmt = null;
		try {
			try {
				String sql = "SELECT * FROM AuthTokens WHERE tokenID = ?;";
				stmt = c.prepareStatement(sql);
				
				//Fill statement with the given authID.
				stmt.setString(1, authID);
				
				//Execute the query and add the values to a new object to be returned.
				logger.log(Level.FINE, "Getting AuthToken.");
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
			throw new DatabaseException(String.format("Get AuthToken failed. : %s ::AD", e.getLocalizedMessage()));
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
				logger.log(Level.FINE, "Getting all AuthTokens.");
				ResultSet rs = stmt.executeQuery();
				
				//Iterate through the ResultSet and create new AuthTokens to be returned in the final Set.
				AuthToken[] all = new AuthToken[rs.getFetchSize()];
				int rowCount = 0;
				while(rs.next()) {
					String id = rs.getString("tokenID");
					String user = rs.getString("userName");
					String person = rs.getString("personID");
					all[rowCount] = new AuthToken(id, user, person);
					rowCount++;
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
			throw new DatabaseException(String.format("Get All AuthTokens failed. : %s ::AD", e.getLocalizedMessage()));
		}
	}
}
