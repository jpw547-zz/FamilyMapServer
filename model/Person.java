package model;

/** The Person class defines the attributes required for a person object to be stored in the Family Map Server database.
 * @author John Werner*/
public class Person {	
	/** The general constructor for a Person object.*/
	public Person() {}
	
	/**The constructor for initializing the attributes of a Person.
	 * @param personID		the personID
	 * @param firstName		the person's first name
	 * @param lastName		the person's last name
	 * @param gender		the person's gender
	 * @param descendant	the descendant of the person's userName
	 * @param father		the person's father (if applicable/known)
	 * @param mother		the person's mother (if applicable/known)
	 * @param spouse		the person's spouse (if applicable/known)*/
	public Person(String personID, String firstName, String lastName, char gender, String descendant, String father, String mother, String spouse) {
		setPersonID(personID);
		setFirstName(firstName);
		setLastName(lastName);
		setGender(gender);
		setDescendant(descendant);
		setFather(father);
		setMother(mother);
		setSpouse(spouse);
	}
	
	/** A unique identifier for the Person object.*/
	private String personID;
	
	/** The Person object's first name.*/
	private String firstName;
	
	/** The Person object's last name.*/
	private String lastName;
	
	/** The Person object's gender, either M or F*/
	private char gender;
	
	/** The userName of the User object that this Person object is connected to. This User is also the root Person object of their family tree.*/
	private String descendant;
	
	/** The identifier of the Person object that represents this Person object's father in the family tree, if applicable.*/
	private String father;
	
	/** The identifier of the Person object that represents this Person object's mother in the family tree, if applicable.*/
	private String mother;
	
	/** The identifier of the Person object that represents this Person object's spouse in the family tree, if applicable.*/
	private String spouse;
	
	
	
	/** Sets the Person object's unique identifier.
	 * @param personID 		a string of letters and numbers to identify the Person.*/
	public void setPersonID(String personID) { this.personID = personID; }
	
	/** Sets the Person object's first name.
	 * @param firstName 	the desired first name*/
	public void setFirstName(String firstName) { this.firstName = firstName; }

	/** Sets the Person object's last name.
	 * @param lastName 		the desired last name*/
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	/** Sets the Person object's gender
	 * @param gender 		the Person's gender, either M or F*/
	public void setGender(char gender) { this.gender = gender; }
	
	/** Sets the Person object's descendant
	 * @param descendant 	the userName of the root of the Person's family tree*/
	public void setDescendant(String descendant) { this.descendant = descendant; }
	
	/** Sets the Person object's father's personID. This doesn't create a new Person object, but just connects two existing Person objects.
	 * @param father 		the personID of the Person object that is the father of this Person object*/
	public void setFather(String father) { this.father = father; }
	
	/** Sets the Person object's mother's personID. This doesn't create a new Person object, but just connects two existing Person objects.
	 * @param mother 		the personID of the Person object that is the mother of this Person object*/
	public void setMother(String mother) { this.mother = mother; }
	
	/** Sets the Person object's spouse's personID. This doesn't create a new Person object, but just connects two existing Person objects.
	 * @param spouse 		the personID of the Person object that is the spouse of this Person object*/
	public void setSpouse(String spouse) { this.spouse = spouse; }
	
	/** @return 			the Person's unique identifier*/
	public String getPersonID() { return personID; }
	
	/** @return 			the Person's first name*/
	public String getFirstName() { return firstName; }
	
	/** @return 			the Person's last name*/
	public String getLastName() { return lastName; }
	
	/** @return 			the Person's gender*/
	public char getGender() { return gender; }
	
	/** @return 			the Person's descendant*/
	public String getDescendant() { return descendant; }
	
	/** @return 			the Person's father's personID*/
	public String getFather() { return father; }
	
	/** @return 			the Person's mother's personID*/
	public String getMother() { return mother; }
	
	/** @return 			the Person's spouse's personID*/
	public String getSpouse() { return spouse; }
}
