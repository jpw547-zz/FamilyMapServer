package results;

import model.AuthToken;

/**The class definition for the attributes of an AuthResult. Used for Login and Registration.
 * <p> Extends the Result class.*/
public class AuthResult {
	
//Constructors
	/**The constructor for an AuthResult object.
	 * @param a				the resulting AuthToken*/
	public AuthResult(AuthToken a) {
		setAuthToken(a);
	}
	
	/**The constructor for an error AuthResult.
	 * @param error			the error message*/
	public AuthResult(String error) {
		setError(error);
	}
	
//Data members
	/**The resulting AuthToken object.*/
	private AuthToken authToken;
	
	/**The possible error message.*/
	private String error;

//Setters
	/**Sets the AuthToken to be returned.
	 * @param authToken		the resulting AuthToken*/
	public void setAuthToken(AuthToken authToken) { this.authToken = authToken; }
	
	/**Sets the error message where applicable.
	 * @param error			the error message*/
	public void setError(String error) { this.error = error; }
	
//Getters
	/**@return the AuthToken to be returned*/
	public AuthToken getAuthToken() { return authToken; }
	
	/**@return the error message*/
	public String getError() { return error; }
}
