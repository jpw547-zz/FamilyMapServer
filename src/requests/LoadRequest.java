package requests;

import model.Event;
import model.Person;
import model.User;

public class LoadRequest {
	private User[] userList;
	private Person[] personList;
	private Event[] eventList;
	
	public LoadRequest(User[] u, Person[] p, Event[] e) {
		setUserList(u);
		setPersonList(p);
		setEventList(e);
	}

	public User[] getUserList() { return userList; }
	public void setUserList(User[] userList) { this.userList = userList; }
	public Person[] getPersonList() { return personList; }
	public void setPersonList(Person[] personList) { this.personList = personList; }
	public Event[] getEventList() { return eventList; }
	public void setEventList(Event[] eventList) { this.eventList = eventList; }
}
