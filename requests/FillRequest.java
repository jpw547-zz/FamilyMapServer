package requests;

/**This class defines the attributes for a FillRequest object.*/
public class FillRequest {
	/**The general constructor for a FillRequest object.
	 * @param userName		the userName to be entered in the database.
	 * @param generations	the number of generations to be created.*/
	public FillRequest(String userName, int generations) {
		setUsername(userName);
		setGenerations(generations);
	}
	
	/**The userName to be created and entered in the database.*/
	private String userName;
	
	/**The number of generations to be created in the family tree of the user. If not specified, the default is 4 generations.*/
	private int generations;
	
	
	
	/**Sets the userName to be entered in the database.
	 * @param userName		the desired userName*/
	public void setUsername(String userName) { this.userName = userName; }
	
	/**Sets the generations to be created.
	 * @param generations	the desired number of generations to be created.*/
	public void setGenerations(int generations) { this.generations = generations; }
	
	/**@return				the userName that is attached to the request.*/
	public String getUsername() { return userName; }
	
	/**@return				the number of generations to be made.*/
	public int getGenerations() { return generations; }
}
