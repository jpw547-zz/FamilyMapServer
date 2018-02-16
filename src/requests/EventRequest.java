package requests;

/**The class that defines requests for Event objects with the server.*/
public class EventRequest {
	/**The constructor for an EventRequest.
	 * @param auth		the AuthTokenID for the user making the request. May be <code>null</code>.
	 * @param event		the eventID for the event in question. May be <code>null</code>.*/
	public EventRequest(String auth, String event) {
		setAuthTokenID(auth);
		setEventID(event);
	}
	
	/**The authorization token for the EventRequest. May be <code>null</code>.*/
	private String AuthTokenID;
	
	/**The identifier for the event in question. May be <code>null</code>.*/
	private String eventID;
	
	/**@return the AuthTokenID for the EventRequest.*/
	public String getAuthTokenID() { return AuthTokenID; }
	
	/**Sets the AuthTokenID for the EventRequest.
	 * @param authTokenID		a unique identifier string for the AuthToken object.*/
	public void setAuthTokenID(String authTokenID) { AuthTokenID = authTokenID; }
	
	/**@return the eventID for the Event object tied to this request.*/
	public String getEventID() { return eventID; }
	
	/**Sets the eventID for the Event tied to this request.
	 * @param eventID			a unique identifier string for the Event object.*/
	public void setEventID(String eventID) { this.eventID = eventID; }
}
