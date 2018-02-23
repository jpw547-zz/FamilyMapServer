package requests;

/**This class defines the attributes for a FillRequest object.*/
public class FillRequest {
	
//Constructors
	/**The general constructor for a FillRequest object.
	 * @param username		the username to be entered in the database.
	 * @param generations	the number of generations to be created.*/
	public FillRequest(String username, int generations) {
		setUsername(username);
		setGenerations(generations);
	}
	
//Data members
	/**The username to be created and entered in the database.*/
	private String username;
	
	/**The number of generations to be created in the family tree of the user. If not specified, the default is 4 generations.*/
	private int generations;
	
//Setters
	/**Sets the username to be entered in the database.
	 * @param username		the desired username*/
	public void setUsername(String username) { this.username = username; }
	
	/**Sets the generations to be created.
	 * @param generations	the desired number of generations to be created.*/
	public void setGenerations(int generations) { this.generations = generations; }
	
//Getters
	/**@return				the username that is attached to the request.*/
	public String getUsername() { return username; }
	
	/**@return				the number of generations to be made.*/
	public int getGenerations() { return generations; }
}
