package services;

import requests.FillRequest;
import results.Result;

/**The class definition for a FillService object.*/
public class FillService {
	/**The general constructor for a FillService object.*/
	public FillService() {}
	
	/**Fill the database with the given information for the user given. Any previous data in the database for the user is cleared out before the database is filled with the new data.
	 * @param f			A FillRequest containing the user and information to fill the database with.
	 * @return			A Result object with the resulting message.*/
	public Result fill(FillRequest f) {
		return null;
	}
}
