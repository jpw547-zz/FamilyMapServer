package requests;

public class PersonRequest {
	private String AuthTokenID;
	private String personID;
	
	public PersonRequest(String auth, String person) {
		setAuthTokenID(auth);
		setPersonID(person);
	}

	public String getAuthTokenID() { return AuthTokenID; }
	public void setAuthTokenID(String authTokenID) { AuthTokenID = authTokenID; }
	public String getPersonID() { return personID; }
	public void setPersonID(String personID) { this.personID = personID; }
}
