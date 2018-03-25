package services;

import java.util.logging.*;

import model.*;
import dao.*;
import requests.EventRequest;
import results.EventResult;

/**The class definition for an EventService object.*/
public class EventService {
	/**The general constructor for an EventService object.*/
	public EventService() {}
	
	/**The Logger object to log statements on the server log.*/
	private static Logger logger;
	static { logger = Logger.getLogger("familymaptest"); }
	
	/**Finds all Events for all family members of the currently logged in user.
	 * @param er			the EventRequest with the AuthToken for the user.
	 * @return				an EventResult object with the response data, or <code>null</code> if no Events are found.*/
	public EventResult getAll(EventRequest er) {
		logger.log(Level.INFO, "Starting GetAll in EventService.");
		Database db = new Database();
		try {
			AuthToken token;
			try {
				//Get AuthToken
				token = db.getAD().getAuthToken(er.getAuthTokenID());
				if(token == null) {
					db.closeConnection(false);
					logger.log(Level.SEVERE, "Failed to get AuthToken. ::Person");
					return new EventResult("Invalid AuthTokenID.");
				}
			} catch (DatabaseException e) {
				db.closeConnection(false);
				logger.log(Level.SEVERE, "Failed to get all Events : Invalid AuthTokenID");
				return new EventResult("Invalid AuthTokenID.");
			}
			
			//Get Persons based on userName from the token.
			Event[] results = db.getED().getAllEvents(token.getUserName());
			db.closeConnection(true);
			logger.log(Level.FINE, "Returning Event array.");
			logger.log(Level.INFO, "Exiting GetAll in EventService.");
			return new EventResult(results);
		} catch (DatabaseException evt) {
			db.closeConnection(false);
			logger.log(Level.SEVERE, String.format("Failed to get all Events : %s", evt.getLocalizedMessage()));
			return new EventResult(String.format("Failed to get all Events : %s", evt.getLocalizedMessage()));
		}
	}
	
	/**Finds a specific Event based on the eventID.
	 * @param er			the EventRequest with the eventID to be found.
	 * @return				an EventResult object with the response data, or <code>null</code> if not found.*/
	public EventResult getEvent(EventRequest er) {
		logger.log(Level.INFO, "Starting GetEvent in EventService.");
		Database db = new Database();
		try {
			AuthToken token;
			try {
				//Get AuthToken
				token = db.getAD().getAuthToken(er.getAuthTokenID());
				if(token == null) {
					logger.log(Level.SEVERE, "Failed to get AuthToken. ::Event");
					return new EventResult("Invalid AuthTokenID.");
				}
			} catch (DatabaseException e) {
				return new EventResult("Invalid AuthTokenID.");
			}
			
			Event result = db.getED().getEvent(er.getEventID());
			if(!result.getDescendant().equals(token.getUserName())) {
				throw new DatabaseException("Not authorized to access that event.");
			}
			db.closeConnection(true);
			logger.log(Level.FINE, "Returning Event.");
			logger.log(Level.INFO, "Exiting GetEvent in EventService.");
			return new EventResult(result);
		} catch (DatabaseException evt) {
			db.closeConnection(false);
			logger.log(Level.SEVERE, String.format("Failed to get Event : %s", evt.getLocalizedMessage()));
			return new EventResult(String.format("Failed to get Event : %s", evt.getLocalizedMessage()));
		}
	}
}
