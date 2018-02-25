package dao;

public class DatabaseException extends Exception {

//Constructors
	public DatabaseException(String m) { setMessage(m); }
	public DatabaseException(String m, Exception e) { setMessage(m); setException(e); }
	
//Data members
	private static final long serialVersionUID = 7058585350050572404L;
	private String message;
	private Exception e;
	
//Setters
	public void setMessage(String x) { this.message = x; }
	
	public void setException(Exception e) { this.e = e; }
	
//Getters
	public String getMessage() {return message; }
	
	public Exception getException() { return e; }
}
