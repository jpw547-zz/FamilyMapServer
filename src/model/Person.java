package model;

/** The Person class defines the attributes required for a person object to be stored in the Family Map Server database.
 * 
 * @author John Werner*/
public class Person {
	/** The general constructor for a Person object.
	 * @returns a new Person object*/
	public Person() {
		
	}
	
	/** A unique identifier for the Person object.*/
	private String personID; // Person’s unique ID
	
	/** The Person object's first name.*/
	private String firstName; // Person’s first name
	
	/** The Person object's last name.*/
	private String lastName; // Person’s last name
	
	/** The Person object's gender, either M or F*/
	private char gender; // Person’s gender (“m” or “f”
	
	/** The userName of the User object that this Person object is connected to. This User is also the root Person object of their family tree.*/
	private String descendant; // Name of user account this person belongs to
	
	/** The identifier of the Person object that represents this Person object's father in the family tree, if applicable.*/
	private String father; // ID of person’s father [OPTIONAL, can be missing]
	
	/** The identifier of the Person object that represents this Person object's mother in the family tree, if applicable.*/
	private String mother; // ID of person’s mother [OPTIONAL, can be missing]
	
	/** The identifier of the Person object that represents this Person object's spouse in the family tree, if applicable.*/
	private String spouse; // ID of person’s spouse [OPTIONAL, can be missing]
	
	
	/** @returns the Person's unique identifier*/
	public String getPersonID() { return personID; }
	
	/** Sets the Person object's unique identifier.
	 * @param personID a string of letters and numbers to identify the Person.*/
	public void setPersonID(String personID) { this.personID = personID; }
	
	/** @returns the Person's first name*/
	public String getFirstName() { return firstName; }
	
	/** Sets the Person object's first name.
	 * @param firstName the desired first name*/
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	/** @returns the Person's last name*/
	public String getLastName() { return lastName; }
	
	/** Sets the Person object's last name.
	 * @param lastName the desired last name*/
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	/** @returns the Person's gender*/
	public char getGender() { return gender; }
	
	/** Sets the Person object's gender
	 * @param gender the Person's gender, either M or F*/
	public void setGender(char gender) { this.gender = gender; }
	
	/** @returns the Person's descendant*/
	public String getDescendant() { return descendant; }
	
	/** Sets the Person object's descendant
	 * @param descendant the userName of the root of the Person's family tree*/
	public void setDescendant(String descendant) { this.descendant = descendant; }
	
	/** @returns the Person's father's personID, and <code>null</code> if empty*/
	public String getFather() { return father; }
	
	/** Sets the Person object's father's personID. This doesn't create a new Person object, but just connects two existing Person objects.
	 * @param father the personID of the Person object that is the father of this Person object*/
	public void setFather(String father) { this.father = father; }
	
	/** @returns the Person's mother's personID, and <code>null</code> if empty*/
	public String getMother() { return mother; }
	
	/** Sets the Person object's mother's personID. This doesn't create a new Person object, but just connects two existing Person objects.
	 * @param mother the personID of the Person object that is the mother of this Person object*/
	public void setMother(String mother) { this.mother = mother; }
	
	/** @returns the Person's spouse's personID, and <code>null</code> if empty*/
	public String getSpouse() { return spouse; }
	
	/** Sets the Person object's spouse's personID. This doesn't create a new Person object, but just connects two existing Person objects.
	 * @param spouse the personID of the Person object that is the spouse of this Person object*/
	public void setSpouse(String spouse) { this.spouse = spouse; }
}
