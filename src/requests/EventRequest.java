package requests;

public class EventRequest {
	private String AuthTokenID;
	private String eventID;
	
	public EventRequest(String auth, String event) {
		setAuthTokenID(auth);
		setEventID(event);
	}

	public String getAuthTokenID() { return AuthTokenID; }
	public void setAuthTokenID(String authTokenID) { AuthTokenID = authTokenID; }
	public String getEventID() { return eventID; }
	public void setEventID(String eventID) { this.eventID = eventID; }
}
