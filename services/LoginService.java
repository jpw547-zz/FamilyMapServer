package services;

import dao.*;
import requests.LoginRequest;
import results.AuthResult;
import java.util.UUID;
import java.util.logging.*;
import model.*;

/**The class definition for a LoginService object.*/
public class LoginService {
	/**The general constructor for a LoginService object.*/
	public LoginService() {}
	
	/**The Logger object to log statements on the server log.*/
	private static Logger logger;
	static { logger = Logger.getLogger("familymaptest"); }
	
	/**Logs the user in with the server.
	 * @param lr		a LoginRequest object that contains the user information.
	 * @return			an AuthResult object with the response information.*/
	public AuthResult login(LoginRequest lr) {
		logger.log(Level.INFO, "Starting login.");
		//Check the username and password and return the personID for the User.
		Database db = new Database();
		User entry;
		try {
			try {
				entry = db.getUD().getUser(lr.getUsername());
			} catch (DatabaseException e) {
				db.closeConnection(false);
				logger.log(Level.WARNING, e.getLocalizedMessage());
				return new AuthResult("Login failed : User not registered."); 
			}
			
			//Check if passwords match.
			if(!entry.getPassword().equals(lr.getPassword())) {
				logger.log(Level.WARNING, "Passwords did not match.");
				db.closeConnection(false);
				return new AuthResult("Login failed : The password entered did not match the password on file.");
			}
			
			//Generate a new AuthToken with the userName and personID.
			String authID = UUID.randomUUID().toString();
			AuthToken a = new AuthToken(authID, lr.getUsername(), entry.getPersonID());
			
			db.getAD().addAuthToken(a);
			db.closeConnection(true);
			logger.log(Level.INFO, "Exiting login.");
			return new AuthResult(a);
		} catch (DatabaseException notFound) {
			db.closeConnection(false);
			logger.log(Level.SEVERE, notFound.getLocalizedMessage());
			return new AuthResult(String.format("Login failed : %s", notFound.getLocalizedMessage()));
		}
	}
}
