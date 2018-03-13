package requests;

/**The class that defines requests for Person objects with the server.*/
public class PersonRequest {
	/**The constructor for an PersonRequest.
	 * @param auth			the AuthTokenID for the user making the request.
	 * @param person		the personID for the Person in question. May be <code>null</code>.*/
	public PersonRequest(String auth, String person) {
		setAuthTokenID(auth);
		setPersonID(person);
	}
	
	/**The authorization token for the PersonRequest.*/
	private String AuthTokenID;
	
	/**The identifier for the Person in question. May be <code>null</code>.*/
	private String personID;
	
	
	
	/**Sets the AuthTokenID for the PersonRequest.
	 * @param authTokenID	a unique identifier string for the AuthToken object.*/
	public void setAuthTokenID(String authTokenID) { AuthTokenID = authTokenID; }
	
	/**Sets the personID for the Person tied to this request.
	 * @param personID		a unique identifier string for the Person object.*/
	public void setPersonID(String personID) { this.personID = personID; }
	
	/**@return 				the AuthTokenID for the PersonRequest.*/
	public String getAuthTokenID() { return AuthTokenID; }
	
	/**@return 				the personID for the Person object tied to this request.*/
	public String getPersonID() { return personID; }
}
