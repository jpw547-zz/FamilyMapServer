package model;

/** The User class defines the attributes for persons who are registered in the database who have access to the Family Map server.
 * @author	John Werner
 */
public class User {
	
//Constructors
	/** General constructor for a User object.*/
	public User() {}
	
	/**A constructor for initializing the attributes of a User.
	 * @param userName		the userName
	 * @param personID		the personID associated with this User
	 * @param email			the email address
	 * @param firstName		the first name of the User
	 * @param lastName		the last name of the User
	 * @param gender		the gender of the User*/
	public User(String userName, String personID, String email, String firstName, String lastName, char gender) {
		setUserName(userName);
		setPassword(personID);
		setEmail(email);
		setFirstName(firstName);
		setLastName(lastName);
		setGender(gender);
	}
	
//Data members
	/** A non-empty String containing the unique identifier for a user.*/
	private String userName; // Non-empty string
	
	/** A non-empty String containing the user's password.*/
	private String password; // Non-empty string
	
	/** A non-empty String containing the user's email address.*/
	private String email; // Non-empty string
	
	/** A non-empty String containing the user's first name.*/
	private String firstName; // Non-empty string
	
	/** A non-empty String containing the user's last name.*/
	private String lastName; // Non-empty string
	
	/** A char containing the user's gender, either 'M' or 'F'.*/
	private char gender; // “f” or “m”
	
	
//Setters
	/** Sets the User object's userName.
	 * @param userName 	the desired userName*/
	public void setUserName(String userName) { this.userName = userName; }
	
	/** Sets the User object's password.
	 * @param password the desired password*/
	public void setPassword(String password) { this.password = password; }
	
	/** Sets the User object's email address.
	 * @param email the desired email address*/
	public void setEmail(String email) { this.email = email; }
	
	/** Sets the User object's first name.
	 * @param firstName the person's first name*/
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	/** Sets the User object's last name.
	 * @param lastName the person's last name*/
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	/** Sets the User object's gender.
	 * @param gender the person's gender, either M or F*/
	public void setGender(char gender) { this.gender = gender; }
	
//Getters
	/** @return the User's userName*/
	public String getUserName() { return userName; }
	
	/** @return the User's password*/
	public String getPassword() { return password; }
	
	/** @return the User's email address*/
	public String getEmail() { return email; }
	
	/** @return the User's first name*/
	public String getFirstName() { return firstName; }
	
	/** @return the User's last name*/
	public String getLastName() { return lastName; }
	
	/** @return the User's gender*/
	public char getGender() { return gender; }
}
