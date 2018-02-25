package model;

/**The Event class defines the attributes required for family history events that can be used in the Family Map Server.*/
public class Event {
	
//Constructors
	/**The general constructor for an Event object.*/
	public Event() {}
	
	/**The constructor for initializing the attributes of an Event.
	 * @param eventID		the eventID
	 * @param personID		the personID
	 * @param descendant	the descendant's userName
	 * @param latitude		the latitude of the event location
	 * @param longitude		the longitude of the event location
	 * @param country		the country of the event location
	 * @param city			the city of the event location
	 * @param eventType		the type of event
	 * @param year			the year the event happened*/
	public Event(String eventID, String personID, String descendant, double latitude, double longitude, String country, String city, String eventType, String year) {
		setEventID(eventID);
		setPersonID(personID);
		setDescendant(descendant);
		setLatitude(latitude);
		setLongitude(longitude);
		setCountry(country);
		setCity(city);
		setEventType(eventType);
		setYear(year);
	}
	
//Data members
	/**A non-empty, unique identifier for the Event object.*/
	private String eventID; // Event’s unique ID (non-empty string)
	
	/**A non-empty identifier for the Person object tied to this event.*/
	private String personID; // ID of the person this event belongs to (non-empty string)
	
	/**A non-empty userName for the User account that this event is tied to. (The root of the personID's family tree.)*/
	private String descendant; // Name of user account this event belongs to (non-empty string)
	
	/**The latitude coordinate of the location of the event.*/
	private double latitude; // Latitude of the event’s location (number)
	
	/**The longitude coordinate of the location of the event.*/
	private double longitude; // Longitude of the event’s location (number)
	
	/**The country of the location of the event.*/
	private String country; // Name of country where event occurred (non-empty string)
	
	/**The city of the location of event.*/
	private String city; // Name of city where event occurred (non-empty string)
	
	/**The type of event. (i.e. "birth", "baptism", etc.)*/
	private String eventType; // Type of event (“birth”, “baptism”, etc.) (non-empty string)
	
	/**The year the event occurred.*/
	private String year; // Year the event occurred (integer formatted as string)
	
//Setters
	/**Sets the Event object's unique identifier.
	 * @param eventID 		the string of letters and numbers to represent the Event object*/
	public void setEventID(String eventID) { this.eventID = eventID; }
	
	/**Sets the Event object's personID.
	 * @param personID 		the identifier for the Person object tied to this Event object.*/
	public void setPersonID(String personID) { this.personID = personID; }
	
	/**Sets the Event object's descendant.
	 * @param descendant 	the userName for the User account that is tied to this event. (the root of the personID's family tree.)*/
	public void setDescendant(String descendant) { this.descendant = descendant; }
	
	/**Sets the Event object's latitude.
	 * @param latitude 		the latitude coordinate of the location of the event*/
	public void setLatitude(double latitude) { this.latitude = latitude; }
	
	/**Sets the Event object's longitude.
	 * @param longitude 	the longitude coordinate of the location of the event*/
	public void setLongitude(double longitude) { this.longitude = longitude; }
	
	/**Sets the Event object's country where the event took place.
	 * @param country 		the country where the event took place*/
	public void setCountry(String country) { this.country = country; }
	
	/**Sets the Event object's city where the event took place.
	 * @param city			the city where the event took place*/
	public void setCity(String city) { this.city = city; }
	
	/**Sets the Event object's type of event.
	 * @param eventType 	the type of event*/
	public void setEventType(String eventType) { this.eventType = eventType; }
	
	/**Sets the Event object's year that the event happened.
	 * @param year 			the year (as a number) formatted as a string*/
	public void setYear(String year) { this.year = year;}
	
//Getters
	/**@return 				the identifier for the Event object*/
	public String getEventID() { return eventID; }
	
	/**@return 				the identifier for the Person object tied to this Event object*/
	public String getPersonID() { return personID; }
	
	/**@return 				the userName of the User account tied to this event (the root of the personID's family tree)*/
	public String getDescendant() { return descendant; }
	
	/**@return 				the latitude coordinate of the location of the event*/
	public double getLatitude() { return latitude; }
	
	/**@return 				the longitude coordinate of the location of the event*/
	public double getLongitude() { return longitude; }
	
	/**@return 				the country where the event took place*/
	public String getCountry() { return country; }
	
	/**@return 				the city where the event took place*/
	public String getCity() { return city; }
	
	/**@return 				the type of event*/
	public String getEventType() { return eventType; }
	
	/**@return 				the year that the event happened*/
	public String getYear() { return year; }
}
