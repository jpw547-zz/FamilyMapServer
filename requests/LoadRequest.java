package requests;

import model.Event;
import model.Person;
import model.User;

/**This class defines the attributes for a LoadRequest.*/
public class LoadRequest {
	
//Constructors
	/**The general constructor for a LoadRequest object.
	 * @param users			the list of Users to be created.
	 * @param persons		the list of Persons to be created.
	 * @param events		the list of Events to be created.*/
	public LoadRequest(User[] users, Person[] persons, Event[] events) {
		setUserList(users);
		setPersonList(persons);
		setEventList(events);
	}
	
//Data members
	/**The list of Users to be created.*/
	private User[] userList;
	
	/**The list of Persons to be created.*/
	private Person[] personList;
	
	/**The list of Events to be created.*/
	private Event[] eventList;
	
//Setters
	/**Sets the list of Users to be created.
	 * @param userList		the list of Users.*/
	public void setUserList(User[] userList) { this.userList = userList; }
	
	/**Sets the list of Persons to be created.
	 * @param personList	the list of Persons.*/
	public void setPersonList(Person[] personList) { this.personList = personList; }
	
	/**Sets the list of Events to be created.
	 * @param eventList		the list of Events.*/
	public void setEventList(Event[] eventList) { this.eventList = eventList; }
	
//Getters
	/**@return				an array of Users.*/
	public User[] getUserList() { return userList; }
	
	/**@return				an array of Persons.*/
	public Person[] getPersonList() { return personList; }
	
	/**@return				an array of Events.*/
	public Event[] getEventList() { return eventList; }
}
