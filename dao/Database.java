package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.*;

public class Database {
	/**The general constructor for a Database object.*/
	public Database() {
		setConnection();
		AD = new AuthDAO(c);
		UD = new UserDAO(c);
		PD = new PersonDAO(c);
		ED = new EventDAO(c);
	}
	
	/**The database Connection object.*/
	private Connection c;
	
	/**A boolean to determine if data should be entered into the testing database or not.*/
	private static boolean testing = false;
	
	/**The Database Access Object for AuthTokens*/
	private AuthDAO AD;
	
	/**The Database Access Object for Users*/
	private UserDAO UD;
	
	/**The Database Access Object for Persons*/
	private PersonDAO PD;
	
	/**The Database Access Object for Events*/
	private EventDAO ED;
	
	/**The Logger object to log statements on the server log.*/
	private static Logger logger;
	static { logger = Logger.getLogger("familymaptest"); }
	
	
	
	/**Establishes a connection to the database.*/
	public void setConnection() {
		logger.log(Level.FINER, "Trying to open database connection.");
		try {
	         Class.forName("org.sqlite.JDBC");
	         if(testing) {
	        	 c = DriverManager.getConnection("jdbc:sqlite:test.db");
	         }
	         if(!testing) {
	        	 c = DriverManager.getConnection("jdbc:sqlite:fmdb.db");
	         }
	         //Prevent changes from auto-committing.
	         c.setAutoCommit(false);
	      } catch (Exception e) {
	         System.err.println(e.getClass().getName() + ": " + e.getMessage());
	      }
	      logger.log(Level.FINER, "Opened database successfully");
	}
	
	/**Sets the value of the testing boolean.
	 * @param t			the value to set "testing" as. Either true or false.*/
	public static void setTesting(boolean t) { testing = t; }

	/**@return			the database Connection object*/
	public Connection getConnection() { return c; }
	
	/**@return 			the DAO for AuthTokens*/
	public AuthDAO getAD() { return AD; }

	/**@return 			the DAO for Users*/
	public UserDAO getUD() { return UD; }

	/**@return 			the DAO for Persons*/
	public PersonDAO getPD() { return PD; }

	/**@return 			the DAO for Events*/
	public EventDAO getED() { return ED; }

	/**Closes the connection to the database.
	 * @param commit	true to commit changes, false to rollback.*/
	public void closeConnection(boolean commit) {
		try {
			try {
				if(commit) { c.commit(); }
				if(!commit) { c.rollback(); }
	            c.close();
	            c = null;
	            logger.log(Level.FINER, "Database connection closed.");
	        } catch (SQLException e) {
				throw new DatabaseException("closeConnection failed");
	        }
		} catch (DatabaseException close) {
			logger.log(Level.SEVERE, close.getMessage());
			logger.log(Level.SEVERE, close.getLocalizedMessage());
		}
	}
}
