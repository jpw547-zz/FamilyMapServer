package services;

import dao.*;
import results.Result;

/**The class definition for a ClearService object.*/
public class ClearService {
	/**The general constructor for a ClearService object.*/
	public ClearService() {}
	
	/**Clears the information in the database.
	 * @return 		a Result object with the resulting message.*/
	public Result clear() {
		//Create Database Access Objects.
		AuthDAO ad = new AuthDAO();
		PersonDAO pd = new PersonDAO();
		EventDAO ed = new EventDAO();
		UserDAO ud = new UserDAO();
		
		//Establish a database connection, delete all rows, proceed to next DAO.
		//AuthToken deletion.
		try {
			ad.setConnection();
			ad.deleteAllAuthTokens();
			ad.closeConnection(true);
		} catch (DatabaseException d) {
			//Upon failure, close the connection and rollback changes.
			try {
				ad.closeConnection(false);
			} catch (DatabaseException e) {
				return new Result(e.getMessage());
			}
			return new Result(d.getMessage());
		}
		
		//Person deletion.
		try {
			pd.setConnection();
			pd.deleteAllPersons();
			pd.closeConnection(true);
		} catch (DatabaseException d) {
			//Upon failure, close the connection and rollback changes.
			try {
				pd.closeConnection(false);
			} catch (DatabaseException e) {
				return new Result(e.getMessage());
			}
			return new Result(d.getMessage());
		}
		
		//Event deletion.
		try {
			ed.setConnection();
			ed.deleteAllEvents();
			ed.closeConnection(true);
		} catch (DatabaseException d) {
			//Upon failure, close the connection and rollback changes.
			try {
				ed.closeConnection(false);
			} catch (DatabaseException e) {
				return new Result(e.getMessage());
			}
			return new Result(d.getMessage());
		}
		
		//User deletion.
		try {
			ud.setConnection();
			ud.deleteAllUsers();
			ud.closeConnection(true);
		} catch (DatabaseException d) {
			//Upon failure, close the connection and rollback changes.
			try {
				ud.closeConnection(false);
			} catch (DatabaseException e) {
				return new Result(e.getMessage());
			}
			return new Result(d.getMessage());
		}
		
		//All deletions passed.
		return new Result("Clear succeeded.");
	}
}
