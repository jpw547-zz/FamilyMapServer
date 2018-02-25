package services;

import dao.*;
import requests.LoginRequest;
import results.AuthResult;
import java.util.UUID;

/**The class definition for a LoginService object.*/
public class LoginService {
	/**The general constructor for a LoginService object.*/
	public LoginService() {}
	
	/**Logs the user in with the server.
	 * @param lr		a LoginRequest object that contains the user information.
	 * @return			an AuthResult object with the response information.*/
	public AuthResult login(LoginRequest lr) {
		//Check the username and password and return the personID for the User.
		String pid = CheckUserInfo(lr);
		if(pid == "error") {
			return new AuthResult("The user's information did not match information in the database. ::Login::");
		}
		if(pid == null) {
			return new AuthResult("The user does not have a Person associated to them. ::Login::");
		}
		
		AuthDAO ad = new AuthDAO();
		//Generate a new AuthToken with the userName and personID.
		String authID = UUID.randomUUID().toString();
		model.AuthToken a = new model.AuthToken(authID, lr.getUsername(), pid);
		try {
			ad.setConnection();
			ad.addAuthToken(a);
			ad.closeConnection(true);
			return new AuthResult(a);
		} catch (DatabaseException e) {
			e.printStackTrace();
			try {
				ad.closeConnection(false);
			} catch (DatabaseException de) {
				de.printStackTrace();
				return new AuthResult("Failed to add, and failed to close. ::Login::");
			}
			return new AuthResult("Failed to add new token to database. ::Login::");
		}
	}
	
	/**Checks the User's information in the database and returns the personID associated with the User.
	 * @return 		the User's personID*/
	private String CheckUserInfo(LoginRequest lr) {
		UserDAO ud = new UserDAO();
		//Check if already registered.
		ud.setConnection();
		model.User entry;
		try {
			entry = ud.getUser(lr.getUsername());
			if(entry == null) {
				ud.closeConnection(false);
				System.out.println("get user returned null.");
				return "error"; 
			}
			
			//Check if passwords match.
			if(!entry.getPassword().equals(lr.getPassword())) {
				System.out.println("password didn't match.");
				ud.closeConnection(false);
				return "error";
			}
			
			//At this point, everything matches so we return the personID.
			ud.closeConnection(true);
			return entry.getPersonID();
		} catch (DatabaseException notFound) {
			try {
				ud.closeConnection(false);
			} catch (DatabaseException e) {
				e.printStackTrace();
			}
			//notFound.printStackTrace();
			return "error";
		}
	}
}
