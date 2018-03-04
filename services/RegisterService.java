package services;

import java.util.UUID;
import java.util.logging.*;

import requests.*;
import results.*;
import dao.*;
import model.*;
import services.DataGenerator;

/**The class definition for a RegisterService object.*/
public class RegisterService {

//Constructors
	/**The general constructor for a RegisterService object.*/
	public RegisterService() {}

//Data members
	private static Logger logger;
	static { logger = Logger.getLogger("familymaptest"); }
	
//Remaining class methods
	/**Registers a user with with the server, and adds their information to the database.
	 * @param rr			a RegisterRequest object with the user information.
	 * @return				an AuthResult object with the response information.*/
	public AuthResult register(RegisterRequest rr) {
		logger.log(Level.INFO, "Starting registration.");
		Database db = new Database();
		//Create a person object for the User.
		Person p = new Person(
				UUID.randomUUID().toString(), rr.getFirstName(), rr.getLastName(), rr.getGender(), rr.getUserName(), "", "", "");
		//Create a new User object to be registered.
		User newguy = new User(rr.getUserName(), rr.getPassword(), rr.getEmail(), rr.getFirstName(), rr.getLastName(), rr.getGender(), p.getPersonID());
		try {
			//The database will throw an exception if the userName is not unique.
			db.getUD().addUser(newguy);
			
			DataGenerator data = new DataGenerator();
			//Make a fake LoadRequest object just to get the data back from the DataGenerator.
			ListResult lr = data.GenerateDefaultAncestorData(p);
			for(int i = 0; i < lr.getPersonList().length; i++) {
				db.getPD().addPerson(lr.getPersonList()[i]);
			}
			
			for(int j = 0; j < lr.getEventList().length; j++) {
				db.getED().addEvent(lr.getEventList()[j]);
			}
			db.closeConnection(true);
			
			logger.log(Level.FINE, "User added and generations created.");
			
			LoginService ls = new LoginService();
			logger.log(Level.INFO, "Exiting registration.");
			return ls.login(new LoginRequest(rr.getUserName(), rr.getPassword()));
		} catch (DatabaseException de) {
			db.closeConnection(false);
			logger.log(Level.SEVERE, String.format("Failed to register : %s", de.getLocalizedMessage()));
			return new AuthResult(String.format("Failed to register : %s", de.getLocalizedMessage()));
		}
	}
}
