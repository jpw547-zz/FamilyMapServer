package model;

public class AuthToken {
	private String authToken; // Non-empty auth token string
	private String userName; // User name passed in with request
	private String personId; // Non-empty string containing the Person ID of the user's Person object
	
	public AuthToken() {
		
	}

	public String getAuthToken() { return authToken; }
	public void setAuthToken(String authToken) { this.authToken = authToken; }
	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }
	public String getPersonId() { return personId; }
	public void setPersonId(String personId) { this.personId = personId;}
}
