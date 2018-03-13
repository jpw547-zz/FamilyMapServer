package services;

import java.util.logging.*;

import model.*;
import dao.*;
import requests.PersonRequest;
import results.PersonResult;

/**The class definition for a PersonService object.*/
public class PersonService {
	/**The general constructor for a PersonService object.*/
	public PersonService() {}
	
	/**The Logger object to log statements on the server log.*/
	private static Logger logger;
	static { logger = Logger.getLogger("familymaptest"); }
	
	/**Finds all Persons for all family members of the currently logged in user.
	 * @param pr			the PersonRequest with the AuthToken for the user.
	 * @return				an PersonResult object with the response data, or <code>null</code> if no Events are found.*/
	public PersonResult getAll(PersonRequest pr) {
		logger.log(Level.INFO, "Starting GetAll in PersonService.");
		Database db = new Database();
		try {
			AuthToken token;
			try {
				//Get AuthToken
				token = db.getAD().getAuthToken(pr.getAuthTokenID());
				if(token == null) {
					logger.log(Level.SEVERE, "Failed to get AuthToken. ::Person");
					return new PersonResult("Invalid AuthTokenID.");
				}
			} catch (DatabaseException e) {
				return new PersonResult("Invalid AuthTokenID.");
			}
			
			
			//Get Persons based on userName from the token.
			Person[] results = db.getPD().getAllPersons(token.getUserName());
			db.closeConnection(true);
			logger.log(Level.FINE, "Returning Person array.");
			logger.log(Level.INFO, "Exiting GetAll in PersonService.");
			return new PersonResult(results);
		} catch (DatabaseException per) {
			db.closeConnection(false);
			logger.log(Level.SEVERE, String.format("Failed to get all Persons : %s", per.getLocalizedMessage()));
			return new PersonResult(String.format("Failed to get all Persons : %s", per.getLocalizedMessage()));
		}
	}
	
	/**Finds a specific Person based on the personID.
	 * @param pr			the PersonRequest with the personID to be found.
	 * @return				an PersonResult object with the response data, or <code>null</code> if not found.*/
	public PersonResult getPerson(PersonRequest pr) {
		logger.log(Level.INFO, "Starting GetPerson in PersonService.");
		Database db = new Database();
		try {
			AuthToken token;
			try {
				//Get AuthToken
				token = db.getAD().getAuthToken(pr.getAuthTokenID());
				if(token == null) {
					logger.log(Level.SEVERE, "Failed to get AuthToken. ::Person");
					return new PersonResult("Invalid AuthTokenID.");
				}
			} catch (DatabaseException e) {
				return new PersonResult("Invalid AuthTokenID.");
			}
			
			Person result = db.getPD().getPerson(pr.getPersonID());
			if(!result.getDescendant().equals(token.getUserName())) {
				throw new DatabaseException("Not authorized to access that person.");
			}
			db.closeConnection(true);
			logger.log(Level.FINE, "Returning Person.");
			logger.log(Level.INFO, "Exiting GetPerson in PersonService.");
			return new PersonResult(result);
		} catch (DatabaseException per) {
			db.closeConnection(false);
			logger.log(Level.SEVERE, String.format("Failed to get Person : %s", per.getLocalizedMessage()));
			return new PersonResult(String.format("Failed to get Person : %s", per.getLocalizedMessage()));
		}
	}
}
