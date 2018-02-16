package requests;

import model.Event;
import model.Person;
import model.User;

/**This class defines the attributes for a LoadRequest.*/
public class LoadRequest {
	/**The general constructor for a LoadRequest object.
	 * @param u				the list of Users to be created.
	 * @param p				the list of Persons to be created.
	 * @param e				the list of Events to be created.*/
	public LoadRequest(User[] u, Person[] p, Event[] e) {
		setUserList(u);
		setPersonList(p);
		setEventList(e);
	}
	
	/**The list of Users to be created.*/
	private User[] userList;
	
	/**The list of Persons to be created.*/
	private Person[] personList;
	
	/**The list of Events to be created.*/
	private Event[] eventList;
	
	/**@return				an array of Users.*/
	public User[] getUserList() { return userList; }
	
	/**Sets the list of Users to be created.
	 * @param userList		the list of Users.*/
	public void setUserList(User[] userList) { this.userList = userList; }
	
	/**@return				an array of Persons.*/
	public Person[] getPersonList() { return personList; }
	
	/**Sets the list of Persons to be created.
	 * @param personList	the list of Persons.*/
	public void setPersonList(Person[] personList) { this.personList = personList; }
	
	/**@return				an array of Events.*/
	public Event[] getEventList() { return eventList; }
	
	/**Sets the list of Events to be created.
	 * @param eventList		the list of Events.*/
	public void setEventList(Event[] eventList) { this.eventList = eventList; }
}
