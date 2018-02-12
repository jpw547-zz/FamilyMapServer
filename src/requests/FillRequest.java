package requests;

public class FillRequest {
	private String person;
	private int generations;
	
	public FillRequest(String person, int generations) {
		setPerson(person);
		setGenerations(generations);
	}

	public String getPerson() { return person; }
	public void setPerson(String person) { this.person = person; }
	public int getGenerations() { return generations; }
	public void setGenerations(int generations) { this.generations = generations; }
}
