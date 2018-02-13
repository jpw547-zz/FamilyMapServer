package requests;

/**This class defines the attributes for a LoginRequest object.*/
public class LoginRequest {
	/**The username of the person making the request.*/
	private String username;
	/**The password of the person making the request.*/
	private String password;
	
	/**The general constructor for a LoginRequest.
	 * @param u			the username for the request.
	 * @param p			the password for the request.
	 * @returns			a new LoginRequest object.*/
	public LoginRequest(String u, String p) {
		setUsername(u);
		setPassword(p);
	}

	/**@returns the username that is attached to the request.*/
	public String getUsername() { return username; }
	/**Sets the username for the request.
	 * @param username 	the desired username for the request.*/
	public void setUsername(String username) { this.username = username; }
	/**@returns the password that is attached to the request.*/
	public String getPassword() { return password; }
	/**Sets the password for the request.
	 * @param password	the desired password for the request.*/
	public void setPassword(String password) { this.password = password; }
}
