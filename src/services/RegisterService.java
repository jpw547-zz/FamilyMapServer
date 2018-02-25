package services;

import handlers.json.*;
import handlers.json.JSONConverter.Names;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import requests.RegisterRequest;
import results.AuthResult;
import dao.*;
import model.*;

/**The class definition for a RegisterService object.*/
public class RegisterService {
	/**The general constructor for a RegisterService object.*/
	public RegisterService() {}
	
	private int DEFAULT_GENERATIONS = 4;
	private String[] men;
	private String[] women;
	private String[] last;
	
	/**Registers a user with with the server, and adds their information to the database.
	 * @param rr			a RegisterRequest object with the user information.
	 * @return				an AuthResult object with the response information.*/
	public AuthResult register(RegisterRequest rr) {
		UserDAO ud = new UserDAO();
		//Create a new User object to be registered.
		User newguy = new User(rr.getUserName(), rr.getPassword(), rr.getEmail(), rr.getFirstName(), rr.getLastName(), rr.getGender(), "");
		try {
			ud.setConnection();
			//The database will throw an exception if the userName is not unique.
			ud.addUser(newguy);
			GenerateDefaultAncestors();
			return null;
		} catch (DatabaseException de) {
			try {
				ud.closeConnection(false);
			} catch (DatabaseException close) {
				close.printStackTrace();
				return new AuthResult("Failed to add, and failed to close. ::Register::");
			}
			return new AuthResult("Failed to add user. ::Register::");
		}
	}
	
	private void GenerateDefaultAncestors(RegisterRequest rr) {
		JSONConverter j = new JSONConverter();
		try {
			men = j.GetNames(Names.MALE);
			women = j.GetNames(Names.FEMALE);
			last = j.GetNames(Names.SURNAME);
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		AddNewGeneration(1, rr);
	}
	
	private void AddNewGeneration(int gen, String descendant, String lastName) {
		if(gen > DEFAULT_GENERATIONS) { return; }
		
		Random rand = new Random();
		
		//Generate a new Person as the father.
		Person father = new Person(
				UUID.randomUUID().toString(),
				men[rand.nextInt(men.length)],
				lastName,
				'M',
				descendant,
				"",
				"",
				"");
		
		//Generate a new Person as the mother.
	}
}
