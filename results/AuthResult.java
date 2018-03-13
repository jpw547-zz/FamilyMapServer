package results;

import model.AuthToken;

/**The class definition for the attributes of an AuthResult. Used for Login and Registration.
 * <p> Extends the Result class.*/
public class AuthResult extends Result{
	/**The constructor for an AuthResult object.
	 * @param a				the resulting AuthToken*/
	public AuthResult(AuthToken a) {
		setAuthToken(a);
	}
	
	/**The constructor for an error AuthResult.
	 * @param error			the error message*/
	public AuthResult(String error) {
		setMessage(error);
	}
	
	/**The resulting AuthToken object.*/
	private AuthToken authToken;

	
	
	/**Sets the AuthToken to be returned.
	 * @param authToken		the resulting AuthToken*/
	public void setAuthToken(AuthToken authToken) { this.authToken = authToken; }
	
	/**@return 				the AuthToken to be returned*/
	public AuthToken getAuthToken() { return authToken; }
}
