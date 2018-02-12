package model;

public class Event {
	private String eventID; // Event’s unique ID (non-empty string)
	private String personID; // ID of the person this event belongs to (non-empty string)
	private String descendant; // Name of user account this event belongs to (non-empty string)
	private double latitude; // Latitude of the event’s location (number)
	private double longitude; // Longitude of the event’s location (number)
	private String country; // Name of country where event occurred (non-empty string)
	private String city; // Name of city where event occurred (non-empty string)
	private String eventType; // Type of event (“birth”, “baptism”, etc.) (non-empty string)
	private String year; // Year the event occurred (integer formatted as string)
	
	public Event() {
		
	}

	public String getEventID() { return eventID; }
	public void setEventID(String eventID) { this.eventID = eventID; }
	public String getPersonID() { return personID; }
	public void setPersonID(String personID) { this.personID = personID; }
	public String getDescendant() { return descendant; }
	public void setDescendant(String descendant) { this.descendant = descendant; }
	public double getLatitude() { return latitude; }
	public void setLatitude(double latitude) { this.latitude = latitude; }
	public double getLongitude() { return longitude; }
	public void setLongitude(double longitude) { this.longitude = longitude; }
	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; }
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	public String getEventType() { return eventType; }
	public void setEventType(String eventType) { this.eventType = eventType; }
	public String getYear() { return year; }
	public void setYear(String year) { this.year = year;}
}
