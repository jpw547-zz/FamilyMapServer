package services;

import java.util.logging.*;

import dao.*;
import results.Result;

/**The class definition for a ClearService object.*/
public class ClearService {
	/**The general constructor for a ClearService object.*/
	public ClearService() {}
	
	private static Logger logger;
	static { logger = Logger.getLogger("familymaptest"); }
	
	/**Clears the information in the database.
	 * @return 		a Result object with the resulting message.*/
	public Result clear() {
		logger.log(Level.INFO, "Starting clear service.");
		//Create Database Access Objects.
		Database db = new Database();
				
		try {
			logger.log(Level.FINE, "Attempting to clear database...");
			//AuthToken deletion.
			db.getAD().deleteAllAuthTokens();
			//Person deletion.
			db.getPD().deleteAllPersons();
			//Event deletion.
			db.getED().deleteAllEvents();
			//User deletion.
			db.getUD().deleteAllUsers();
		} catch (DatabaseException e) {
			db.closeConnection(false);
			logger.log(Level.SEVERE, e.getLocalizedMessage());
			return new Result(String.format("Clear failed : %s", e.getLocalizedMessage()));
		}
		//All deletions passed.
		db.closeConnection(true);
		logger.log(Level.FINE, "Clear succeeded.");
		logger.log(Level.INFO, "Exiting clear service.");
		return new Result("Clear succeeded.");
	}
}
