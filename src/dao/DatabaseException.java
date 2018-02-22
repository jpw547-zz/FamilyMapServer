package dao;

public class DatabaseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7058585350050572404L;
	private String message;
	private Exception e;
	
	public DatabaseException(String m) { setMessage(m); }
	public DatabaseException(String m, Exception e) { setMessage(m); setException(e); }
	
	public void setMessage(String x) { this.message = x; }
	
	public String getMessage() {return message; }
	
	public void setException(Exception e) { this.e = e; }
	
	public Exception getException() { return e; }
}
