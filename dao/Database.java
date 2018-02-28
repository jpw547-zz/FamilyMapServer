package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.*;

public class Database {
//Constructors
	/**The general constructor for an AuthDAO object.*/
	public Database() {
		AD = new AuthDAO();
		UD = new UserDAO();
		PD = new PersonDAO();
		ED = new EventDAO();
		setConnection();
	}
	
//Data members
	/**The SQL Database Connection object.*/
	private static Connection c;
	
	private AuthDAO AD;
	private UserDAO UD;
	private PersonDAO PD;
	private EventDAO ED;
	
	private static Logger logger;
	
	static {
        logger = Logger.getLogger("familymaptest");
    }
	
//Setters
	/**Establishes a connection to the SQL database.*/
	public void setConnection() {
		logger.log(Level.FINE, "Trying to open database connection.");
		try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:fmdb.db");
	         c.setAutoCommit(false);
	      } catch (Exception e) {
	         System.err.println(e.getClass().getName() + ": " + e.getMessage());
	      }
	      logger.log(Level.FINE, "Opened database successfully");
	}
	
//	public void setAD() { AD = new AuthDAO(); }
//	
//	public void setUD() { UD = new UserDAO(); }
//	
//	public void setPD() { PD = new PersonDAO(); }
//	
//	public void setED() { ED = new EventDAO(); }
	
//Getters
	/**@return		the database Connection object*/
	public static Connection getConnection() { return c; }
	
	public AuthDAO getAD() { return AD; }

	public UserDAO getUD() { return UD; }

	public PersonDAO getPD() { return PD; }

	public EventDAO getED() { return ED; }

//Remaining class methods
	/**Closes the connection to the database.
	 * @param commit	true to commit changes, false to rollback.*/
	public void closeConnection(boolean commit) {
		try {
			try {
				if(commit) { c.commit(); }
				if(!commit) { c.rollback(); }
	            c.close();
	            c = null;
	            logger.log(Level.FINE, "Database connection closed.");
	        } catch (SQLException e) {
				throw new DatabaseException("closeConnection failed");
	        }
		} catch (DatabaseException close) {
			logger.log(Level.SEVERE, close.getMessage());
			logger.log(Level.SEVERE, close.getLocalizedMessage());
		}
	}
}
