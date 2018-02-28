package dao;

public class DatabaseException extends Exception {

//Constructors
	public DatabaseException(String m) { setMessage(m); }
	
//Data members
	private static final long serialVersionUID = 7058585350050572404L;
	private String message;
	
//Setters
	public void setMessage(String x) { this.message = x; }
	
//Getters
	public String getMessage() {return message; }
}
