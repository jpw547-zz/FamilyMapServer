package services;

import requests.EventRequest;
import results.EventResult;

/**The class definition for an EventService object.*/
public class EventService {
	/**The general constructor for an EventService object.*/
	public EventService() {}
	
	/**Finds all Events for all family members of the currently logged in user.
	 * @param er			the EventRequest with the AuthToken for the user.
	 * @return				an EventResult object with the response data, or <code>null</code> if no Events are found.*/
	public EventResult getAll(EventRequest er) {
		return null;
	}
	
	/**Finds a specific Event based on the eventID.
	 * @param er			the EventRequest with the eventID to be found.
	 * @return				an EventResult object with the response data, or <code>null</code> if not found.*/
	public EventResult getEvent(EventRequest er) {
		return null;
	}
}
