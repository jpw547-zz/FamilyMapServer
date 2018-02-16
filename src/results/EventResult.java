package results;

import model.Event;

/**The class definition for the attributes of an EventResult.
 * <p> Extends the Result class.*/
public class EventResult extends Result{
	/**Constructor for returning one Event.
	 * @param e			the event to be returned*/
	public EventResult(Event e) {
		setEvent(e);
	}
	
	/**Constructor for returning an array of Events.
	 * @param d			the Event array to be returned*/
	public EventResult(Event[] d) {
		setData(d);
	}
	
	/**The constructor for an error EventResult.
	 * @param error				the error message*/
	public EventResult(String error) {
		setError(error);
	}
	
	/**The Event to be returned.*/
	private Event event;
	
	/**The array of Events to be returned.*/
	private Event[] data;
	
	/**The possible error message.*/
	private String error;
	
	/**Sets the Event to be returned.
	 * @param event		the Event to be returned*/
	public void setEvent(Event event) { this.event = event; }
	
	/**@return			the Event to return*/
	public Event getEvent() { return event; }
	
	/**Sets the data to be returned.
	 * @param data		the Event array to be returned*/
	public void setData(Event[] data) { this.data = data; }
	
	/**@return			the Event array data*/
	public Event[] getData() { return data; }
	
	/**Sets the error message where applicable.
	 * @param error				the error message*/
	public void setError(String error) { this.error = error; }
	
	/**@return					the error message*/
	public String getError() { return error; }
}
