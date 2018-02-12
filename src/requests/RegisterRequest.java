package requests;

public class RegisterRequest {
	private String userName; // Non-empty string
	private String password; // Non-empty string
	private String email; // Non-empty string
	private String firstName; // Non-empty string
	private String lastName; // Non-empty string
	private char gender; // “f” or “m”
	
	public RegisterRequest(String u, String p, String e, String f, String l, char g) {
		setUserName(u);
		setPassword(p);
		setEmail(e);
		setFirstName(f);
		setLastName(l);
		setGender(g);
	}

	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public char getGender() { return gender; }
	public void setGender(char gender) { this.gender = gender; }
}
