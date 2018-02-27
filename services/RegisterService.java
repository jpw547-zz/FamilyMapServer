package services;

import handlers.json.*;
import handlers.json.JSONConverter.Names;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import requests.LoginRequest;
import requests.RegisterRequest;
import results.AuthResult;
import dao.*;
import model.*;

/**The class definition for a RegisterService object.*/
public class RegisterService {

//Constructors
	/**The general constructor for a RegisterService object.*/
	public RegisterService() {}

//Data members
	private int DEFAULT_GENERATIONS = 4;
	private String[] men;
	private String[] women;
	private String[] last;
	
//Remaining class methods
	/**Registers a user with with the server, and adds their information to the database.
	 * @param rr			a RegisterRequest object with the user information.
	 * @return				an AuthResult object with the response information.*/
	public AuthResult register(RegisterRequest rr) {
		UserDAO ud = new UserDAO();
		PersonDAO pd = new PersonDAO();
		//Create a person object for the User.
		Person p = new Person(
				UUID.randomUUID().toString(),
				rr.getFirstName(),
				rr.getLastName(),
				rr.getGender(),
				rr.getUserName(),
				"",
				"",
				"");
		//Create a new User object to be registered.
		User newguy = new User(rr.getUserName(), rr.getPassword(), rr.getEmail(), rr.getFirstName(), rr.getLastName(), rr.getGender(), p.getPersonID());
		try {
			ud.setConnection();
			//The database will throw an exception if the userName is not unique.
			ud.addUser(newguy);
			ud.closeConnection(true);
			
			
			pd.setConnection();
			pd.addPerson(p);
			pd.closeConnection(true);
			
			GenerateDefaultAncestors(p);
			
			LoginService ls = new LoginService();
			return ls.login(new LoginRequest(rr.getUserName(), rr.getPassword()));
		} catch (DatabaseException de) {
			try {
				if(ud.getConnection() != null) {
					ud.closeConnection(false);
				}
				if(pd.getConnection() != null) {
					pd.closeConnection(false);
				}
			} catch (DatabaseException close) {
				close.printStackTrace();
				return new AuthResult("Failed to add, and failed to close. ::Register::");
			}
			return new AuthResult("Failed to add user. ::Register::");
		}
	}
	
	private void GenerateDefaultAncestors(Person start) {
		JSONConverter j = new JSONConverter();
		try {
			//Get the list of possible names for the Persons we will generate.
			men = j.GetNames(Names.MALE);
			women = j.GetNames(Names.FEMALE);
			last = j.GetNames(Names.SURNAME);
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		AddNewGeneration(1, start);
	}
	
	private void AddNewGeneration(int gen, Person child) {
		//Exit out of the function if we are past the number of generations that we should create.
		if(gen > DEFAULT_GENERATIONS) { return; }
		
		//Set up our instance of a Random object to randomly pick names.
		Random rand = new Random();
		
		//Create ID's for the father and mother and assign them to the child.
		String fatherID = UUID.randomUUID().toString();
		String motherID = UUID.randomUUID().toString();
		child.setFather(fatherID);
		child.setMother(motherID);
		
		//Generate a new Person as the father.
		Person father = new Person(
				fatherID,
				men[rand.nextInt(men.length)],
				child.getLastName(),
				'M',
				child.getDescendant(),
				"",
				"",
				motherID);
		
		//Generate a new Person as the mother.
		Person mother = new Person(
				motherID,
				women[rand.nextInt(men.length)],
				last[rand.nextInt(last.length)],
				'F',
				child.getDescendant(),
				"",
				"",
				fatherID);
		
		//Insert these Person objects into the database.
		PersonDAO pdao = new PersonDAO();
		pdao.setConnection();
		try {
			//Update the child Person object.
			pdao.modifyPerson(child);
			//Add the father and mother
			pdao.addPerson(father);
			pdao.addPerson(mother);
			pdao.closeConnection(true);
		} catch (DatabaseException de) {
			try {
				pdao.closeConnection(false);
			} catch (DatabaseException close) {
				close.printStackTrace();
			}
			de.printStackTrace();
		}
		//Increment the generation number.
		gen++;
		//Create a new generation based off of the father and mother.
		AddNewGeneration(gen, father);
		AddNewGeneration(gen, mother);
	}
}
