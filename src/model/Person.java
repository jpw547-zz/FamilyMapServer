package model;

public class Person {
	private String personID; // Person’s unique ID
	private String firstName; // Person’s first name
	private String lastName; // Person’s last name
	private char gender; // Person’s gender (“m” or “f”
	private String descendant; // Name of user account this person belongs to
	private String father; // ID of person’s father [OPTIONAL, can be missing]
	private String mother; // ID of person’s mother [OPTIONAL, can be missing]
	private String spouse; // ID of person’s spouse [OPTIONAL, can be missing]
	
	public Person() {
		
	}
	
	public String getPersonID() { return personID; }
	public void setPersonID(String personID) { this.personID = personID; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public char getGender() { return gender; }
	public void setGender(char gender) { this.gender = gender; }
	public String getDescendant() { return descendant; }
	public void setDescendant(String descendant) { this.descendant = descendant; }
	public String getFather() { return father; }
	public void setFather(String father) { this.father = father; }
	public String getMother() { return mother; }
	public void setMother(String mother) { this.mother = mother; }
	public String getSpouse() { return spouse; }
	public void setSpouse(String spouse) { this.spouse = spouse; }
}
