package requests;

public class LoginRequest {
	private String username;
	private String password;
	
	public LoginRequest(String u, String p) {
		setUsername(u);
		setPassword(p);
	}

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}
