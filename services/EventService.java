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
	
	private static Logger logger;
	static { logger = Logger.getLogger("familymaptest"); }
	
	/**Finds all Events for all family members of the currently logged in user.
	 * @param er			the EventRequest with the AuthToken for the user.
	 * @return				an EventResult object with the response data, or <code>null</code> if no Events are found.*/
	public EventResult getAll(EventRequest er) {
		Database db = new Database();
		try {
			//Get AuthToken
			AuthToken token = db.getAD().getAuthToken(er.getAuthTokenID());
			if(token == null) {
				logger.log(Level.SEVERE, "Failed to get AuthToken. ::Person");
				return new EventResult("Invalid AuthTokenID.");
			}
			
			//Get Persons based on userName from the token.
			Event[] results = db.getED().getAllEvents(token.getUserName());
			db.closeConnection(true);
			logger.log(Level.FINE, "Returning Event array.");
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
		Database db = new Database();
		try {
			Event result = db.getED().getEvent(er.getEventID());
			db.closeConnection(true);
			logger.log(Level.FINE, "Returning Event.");
			return new EventResult(result);
		} catch (DatabaseException evt) {
			db.closeConnection(false);
			logger.log(Level.SEVERE, String.format("Failed to get Event : %s", evt.getLocalizedMessage()));
			return new EventResult(String.format("Failed to get Event : %s", evt.getLocalizedMessage()));
		}
	}
}
