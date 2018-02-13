package model;

/**The class definition for the Authorization Tokens used by the Family Map Server.
 * @author John Werner*/
public class AuthToken {
	/**The general constructor for an AuthToken object.
	 * <p>
	 * @param authToken		the unique identifier for the AuthToken object
	 * @param userName 		the userName for the User object tied to this AuthToken
	 * @param personID 		the identifier for the Person object tied to the User for this AuthToken
	 * @returns 			an AuthToken object*/
	public AuthToken(String authTokenID, String userName, String personID) {
		setAuthTokenID(authTokenID);
		setUserName(userName);
		setPersonId(personID);
	}

	/**The identifier for this AuthToken object.*/
	private String authTokenID; // Non-empty auth token string
	
	/**The userName for the User object tied to this AuthToken.*/
	private String userName; // User name passed in with request
	
	/**The personID for the Person object tied to this AuthToken.*/
	private String personId; // Non-empty string containing the Person ID of the user's Person object
	
	
	/**@returns		the identifier for this AuthToken*/
	public String getAuthTokenID() { return authTokenID; }
	
	/**Sets the identifier for this AuthToken.
	 * @param authTokenID		a unique identifier for this AuthToken*/
	public void setAuthTokenID(String authTokenID) { this.authTokenID = authTokenID; }
	
	/**@returns		the userName for the User object tied to this AuthToken*/
	public String getUserName() { return userName; }
	
	/**Sets the userName for the User object tied to this AuthToken.
	 * @param userName			the userName for the User object tied to this AuthToken*/
	public void setUserName(String userName) { this.userName = userName; }
	
	/**@returns		the identifier for the Person object tied to this AuthToken*/
	public String getPersonId() { return personId; }
	
	/**Sets the identifier for the Person object tied to this AuthToken.
	 * @param personId		the identifier for the Person object tied to this AuthToken*/
	public void setPersonId(String personId) { this.personId = personId;}
}
