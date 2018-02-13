package requests;

/**This class defines the attributes for a FillRequest object.*/
public class FillRequest {
	/**The username to be created and entered in the database.*/
	private String username;
	/**The number of generations to be created in the family tree of the user. If not specified, the default is 4 generations.*/
	private int generations;
	
	/**The general constructor for a FillRequest object.
	 * @param username		the username to be entered in the database.
	 * @param generations	the number of generations to be created.*/
	public FillRequest(String username, int generations) {
		setUsername(username);
		setGenerations(generations);
	}

	/**@returns				the username that is attached to the request.*/
	public String getUsername() { return username; }
	/**Sets the username to be entered in the database.
	 * @param username		the desired username*/
	public void setUsername(String username) { this.username = username; }
	/**@returns				the number of generations to be made.*/
	public int getGenerations() { return generations; }
	/**Sets the generations to be created.
	 * @param generations	the desired number of generations to be created.*/
	public void setGenerations(int generations) { this.generations = generations; }
}
