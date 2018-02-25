package requests;

/**This class defines the attributes for a RegisterRequest object.*/
public class RegisterRequest {
	
//Constructors
	/**The general constructor for a RegisterRequest object.
	 * @param userName		the userName to be registered.
	 * @param password		the password for the user.
	 * @param email			the email address for the user.
	 * @param firstName		the first name of the user.
	 * @param lastName		the last name of the user.
	 * @param gender		the gender of the user.*/
	public RegisterRequest(String userName, String password, String email, String firstName, String lastName, char gender) {
		setUserName(userName);
		setPassword(password);
		setEmail(email);
		setFirstName(firstName);
		setLastName(lastName);
		setGender(gender);
	}
	
//Data members
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

//Setters
	/**Sets the userName for this request.
	 * @param userName		the desired userName.*/
	public void setUserName(String userName) { this.userName = userName; }
	
	/**Sets the password for this request.
	 * @param password		the desired password.*/
	public void setPassword(String password) { this.password = password; }
	
	/**Sets the email address for this request.
	 * @param email		the desired email address.*/
	public void setEmail(String email) { this.email = email; }
	
	/**Sets the first name for this request.
	 * @param firstName		the desired first name.*/
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	/**Sets the last name for this request.
	 * @param lastName		the desired last name.*/
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	/**Sets the user's gender for this request.
	 * @param gender		the desired gender.*/
	public void setGender(char gender) { this.gender = gender; }
	
//Getters
	/**@return the userName that is attached to the request.*/
	public String getUserName() { return userName; }
	
	/**@return the password that is attached to the request.*/
	public String getPassword() { return password; }
	
	/**@return the email address that is attached to the request.*/
	public String getEmail() { return email; }
	
	/**@return the user's first name that is attached to the request.*/
	public String getFirstName() { return firstName; }
	
	/**@return the user's last name that is attached to the request.*/
	public String getLastName() { return lastName; }
	
	/**@return the user's gender that is attached to the request.*/
	public char getGender() { return gender; }	
}
