package dao;

public class DatabaseException extends Exception {
	public DatabaseException(String m) { setMessage(m); }
	
	private static final long serialVersionUID = 7058585350050572404L;
	private String message;
	

	public void setMessage(String x) { this.message = x; }
	
	public String getMessage() { return message; }
}
