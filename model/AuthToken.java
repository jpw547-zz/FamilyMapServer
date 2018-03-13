package model;

/**The class definition for the Authorization Tokens used by the Family Map Server.
 * @author John Werner*/
public class AuthToken {
	/**The general constructor for an AuthToken object.*/
	public AuthToken() {}
	
	/**The constructor for initializing the attributes of an AuthToken.
	 * @param authTokenID	the unique identifier for the AuthToken object
	 * @param userName 		the userName for the User object tied to this AuthToken
	 * @param personID 		the identifier for the Person object tied to the User for this AuthToken*/
	public AuthToken(String authTokenID, String userName, String personID) {
		setAuthTokenID(authTokenID);
		setUserName(userName);
		setPersonID(personID);
	}

	/**The identifier for this AuthToken object.*/
	private String authTokenID;
	
	/**The userName for the User object tied to this AuthToken.*/
	private String userName;
	
	/**The personID for the Person object tied to this AuthToken.*/
	private String personID;
	
	
	
	/**Sets the identifier for this AuthToken.
	 * @param authTokenID	a unique identifier for this AuthToken*/
	public void setAuthTokenID(String authTokenID) { this.authTokenID = authTokenID; }
	
	/**Sets the userName for the User object tied to this AuthToken.
	 * @param userName		the userName for the User object tied to this AuthToken*/
	public void setUserName(String userName) { this.userName = userName; }
	
	/**Sets the identifier for the Person object tied to this AuthToken.
	 * @param personID		the identifier for the Person object tied to this AuthToken*/
	public void setPersonID(String personID) { this.personID = personID;}

	/**@return				the identifier for this AuthToken*/
	public String getAuthTokenID() { return authTokenID; }
	
	/**@return				the userName for the User object tied to this AuthToken*/
	public String getUserName() { return userName; }
	
	/**@return				the identifier for the Person object tied to this AuthToken*/
	public String getPersonID() { return personID; }
}
