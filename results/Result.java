package results;

/**The class that defines the attributes for a Result.*/
public class Result {
	
//Constructors
	/**The general constructor for a Result object.*/
	public Result() {}
	
	/**The constructor for a Result object.
	 * @param message	the result message*/
	public Result(String message) {
		setMessage(message);
	}
	
//Data members
	/**The result message.*/
	private String message;
	
//Setters
	/**Sets the result message.
	 * @param message	the message of the result*/
	public void setMessage(String message) { this.message = message; }
	
//Getters
	/**@return the result message*/
	public String getMessage() { return message; }
}
