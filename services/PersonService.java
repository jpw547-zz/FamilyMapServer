package services;

import requests.PersonRequest;
import results.PersonResult;

/**The class definition for a PersonService object.*/
public class PersonService {
	/**The general constructor for a PersonService object.*/
	public PersonService() {}
	
	/**Finds all Persons for all family members of the currently logged in user.
	 * @param pr			the PersonRequest with the AuthToken for the user.
	 * @return				an PersonResult object with the response data, or <code>null</code> if no Events are found.*/
	public PersonResult getAll(PersonRequest pr) {
		return null;
	}
	
	/**Finds a specific Person based on the personID.
	 * @param pr			the PersonRequest with the personID to be found.
	 * @return				an PersonResult object with the response data, or <code>null</code> if not found.*/
	public PersonResult getPerson(PersonRequest pr) {
		return null;
	}
}
