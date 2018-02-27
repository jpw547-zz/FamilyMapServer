package services;

import model.*;
import dao.*;
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
		AuthToken token = GetTokenFromID(pr.getAuthTokenID());
		if(token == null) {
			System.out.println("Failed to get the AuthToken.....");
			return new PersonResult("::Person:: Failed to get AuthToken");
		}
		System.out.println(token.getUserName());
		PersonDAO pd = new PersonDAO();
		pd.setConnection();
		try {
			Person[] results = pd.getAllPersons(token.getUserName());
			pd.closeConnection(true);
			System.out.println("Apparent successful result.");
			return new PersonResult(results);
		} catch (DatabaseException per) {
			try {
				pd.closeConnection(false);
			} catch (DatabaseException close) {
				close.printStackTrace();
				return new PersonResult(String.format("::Person:: Failed to get all Persons, and failed to close : %s", close.getLocalizedMessage()));
			}
			per.printStackTrace();
			return new PersonResult(String.format("::Person:: Failed to get all Persons : %s", per.getLocalizedMessage()));
		}
	}
	
	/**Finds a specific Person based on the personID.
	 * @param pr			the PersonRequest with the personID to be found.
	 * @return				an PersonResult object with the response data, or <code>null</code> if not found.*/
	public PersonResult getPerson(PersonRequest pr) {
		PersonDAO pd = new PersonDAO();
		pd.setConnection();
		try {
			Person result = pd.getPerson(pr.getPersonID());
			pd.closeConnection(true);
			return new PersonResult(result);
		} catch (DatabaseException per) {
			try {
				pd.closeConnection(false);
			} catch (DatabaseException close) {
				close.printStackTrace();
				return new PersonResult(String.format("::Person:: Failed to get Person, and failed to close : %s", close.getLocalizedMessage()));
			}
			per.printStackTrace();
			return new PersonResult(String.format("::Person:: Failed to get Person : %s", per.getLocalizedMessage()));
		}
	}
	
	private AuthToken GetTokenFromID(String authID) {
		AuthDAO ad = new AuthDAO();
		
		ad.setConnection();
		try {
			AuthToken a = ad.getAuthToken(authID);
			ad.closeConnection(true);
			return a;
		} catch (DatabaseException auth) {
			try {
				ad.closeConnection(false);
			} catch (DatabaseException close) {
				close.printStackTrace();
			}
			auth.printStackTrace();
			return null;
		}
	}
}
