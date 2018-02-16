package requests;

/**This class defines the attributes for a RegisterRequest object.*/
public class RegisterRequest {
	/**The general constructor for a RegisterRequest object.
	 * @param u			the userName to be registered.
	 * @param p			the password for the user.
	 * @param e			the email address for the user.
	 * @param f			the first name of the user.
	 * @param l			the last name of the user.
	 * @param g			the gender of the user.*/
	public RegisterRequest(String u, String p, String e, String f, String l, char g) {
		setUserName(u);
		setPassword(p);
		setEmail(e);
		setFirstName(f);
		setLastName(l);
		setGender(g);
	}
	
	/**The userName to be registered.*/
	private String userName; // Non-empty string
	/**The password to be registered.*/
	private String password; // Non-empty string
	/**The email address for the person to be registered.*/
	private String email; // Non-empty string
	/**The first name of the person to be registered.*/
	private String firstName; // Non-empty string
	/**The last name of the person to be registered.*/
	private String lastName; // Non-empty string
	/**The gender of the person to be registered.*/
	private char gender; // “f” or “m”

	/**@return the userName that is attached to the request.*/
	public String getUserName() { return userName; }
	/**Sets the userName for this request.
	 * @param userName		the desired userName.*/
	public void setUserName(String userName) { this.userName = userName; }
	/**@return the password that is attached to the request.*/
	public String getPassword() { return password; }
	/**Sets the password for this request.
	 * @param password		the desired password.*/
	public void setPassword(String password) { this.password = password; }
	/**@return the email address that is attached to the request.*/
	public String getEmail() { return email; }
	/**Sets the email address for this request.
	 * @param email		the desired email address.*/
	public void setEmail(String email) { this.email = email; }
	/**@return the user's first name that is attached to the request.*/
	public String getFirstName() { return firstName; }
	/**Sets the first name for this request.
	 * @param firstName		the desired first name.*/
	public void setFirstName(String firstName) { this.firstName = firstName; }
	/**@return the user's last name that is attached to the request.*/
	public String getLastName() { return lastName; }
	/**Sets the last name for this request.
	 * @param lastName		the desired last name.*/
	public void setLastName(String lastName) { this.lastName = lastName; }
	/**@return the user's gender that is attached to the request.*/
	public char getGender() { return gender; }
	/**Sets the user's gender for this request.
	 * @param gender		the desired gender.*/
	public void setGender(char gender) { this.gender = gender; }
}
