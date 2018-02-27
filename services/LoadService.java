package services;

import dao.*;
import requests.LoadRequest;
import results.Result;

/**The class definition for a LoadService object.*/
public class LoadService {
	
//Constructors
	/**The general constructor for a LoadService object.*/
	public LoadService() {}
	
//Remaining class methods
	/**Clears all database information and then loads the given data into the database.
	 * @param lr			a LoadRequest object with the information to insert.
	 * @return				a Result object with the resulting message.*/
	public Result load(LoadRequest lr) {
		//Clear the database.
		ClearService cs = new ClearService();
		Result clear = cs.clear();
		if(!clear.getMessage().equals("Clear succeeded.")) {
			return new Result(String.format("::Load:: Failed to clear database : %s", clear.getMessage()));
		}
		
		//Load Users
		UserDAO ud = new UserDAO();
		ud.setConnection();
		try {
			for(int i = 0; i < lr.getUserList().length; i++) {
				ud.addUser(lr.getUserList()[i]);
			}
			ud.closeConnection(true);
		} catch (DatabaseException u) {
			try {
				ud.closeConnection(false);
			} catch (DatabaseException close) {
				close.printStackTrace();
				return new Result(String.format("::Load:: Failed to add Users and failed to close : %s", close.getLocalizedMessage()));
			}
			u.printStackTrace();
			return new Result(String.format("::Load:: Failed to add Users from list : %s", u.getLocalizedMessage()));
		} 
		
		//Load Persons
		PersonDAO pd = new PersonDAO();
		pd.setConnection();
		try {
			for(int j = 0; j < lr.getPersonList().length; j++) {
				pd.addPerson(lr.getPersonList()[j]);
			}
			pd.closeConnection(true);
		} catch (DatabaseException p) {
			try {
				pd.closeConnection(false);
			} catch (DatabaseException close) {
				close.printStackTrace();
				return new Result(String.format("::Load:: Failed to add Persons and failed to close : %s", close.getLocalizedMessage()));
			}
			p.printStackTrace();
			return new Result(String.format("::Load:: Failed to add Persons from list : %s", p.getLocalizedMessage()));
		}
		
		//Load Events
		EventDAO ed = new EventDAO();
		ed.setConnection();
		try {
			for(int k = 0; k < lr.getEventList().length; k++) {
				ed.addEvent(lr.getEventList()[k]);
			}
			ed.closeConnection(true);
		} catch (DatabaseException e) {
			try {
				ed.closeConnection(false);
			} catch (DatabaseException close) {
				close.printStackTrace();
				return new Result(String.format("::Load:: Failed to add Events and failed to close : %s", close.getLocalizedMessage()));
			}
			e.printStackTrace();
			return new Result(String.format("::Load:: Failed to add Events from list : %s", e.getLocalizedMessage()));
		}
		
		return new Result(String.format("Successfully added %s users, %s persons, and %s events to the database.", lr.getUserList().length, lr.getPersonList().length, lr.getEventList().length));
	}
}
