package handlers.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import results.*;

import com.google.gson.*;

public class JSONConverter {
	/**The constructor for a JSONConverter object.*/
	public JSONConverter() {
		Jason = new GsonBuilder().setPrettyPrinting().create();
	}
	
	/**The Gson object that does the generalized converting.*/
	private Gson Jason;
	
	/**The array of Locations to be read in from a JSON file.*/
	private LocationData locations;
	
	
	
	/**Converts an Object into a JSON string.
	 * @param o			the Object to be converted.
	 * @return 			a JSON string made from the object's attributes.*/
	public String ObjectToJSON(Object o) {
		if(o.getClass() == PersonResult.class) {
			PersonResult per = (PersonResult)o;
			if(per.getPerson() != null) {
				return Jason.toJson(per.getPerson());
			}
		}
		if(o.getClass() == EventResult.class) {
			EventResult evn = (EventResult)o;
			if(evn.getEvent() != null) {
				return Jason.toJson(evn.getEvent());
			}
		}
		if(o.getClass() == AuthResult.class) {
			AuthResult aut = (AuthResult)o;
			if(aut.getAuthToken() != null) {
				return Jason.toJson(aut.getAuthToken());
			}
		}
		return Jason.toJson(o);
	}
	
	/**Converts a JSON string into an object.
	 * @param j			the JSON String to be converted.
	 * @param oClass	the class literal for the class of Object that the String will be converted into.
	 * @return			the object of the specified class, made from the JSON String.*/
	@SuppressWarnings("hiding")
	public <Object> Object JSONToObject(String j, Class<Object> oClass) {
		return Jason.fromJson(j, oClass);
	}
	
	/**Converts a Reader into an object.
	 * @param r			the Reader containing the information to be converted.
	 * @param oClass	the class literal for the class of Object that the String will be converted into.
	 * @return			the object of the specified class, made from the Reader.*/
	@SuppressWarnings("hiding")
	public <Object> Object JSONToObject(Reader r, Class<Object> oClass) {
		return Jason.fromJson(r, oClass);
	}
	
	/**Gets the list of names needed for the other services from the JSON file.
	 * @param type		the enum for the type of names to get (MALE, FEMALE, SURNAME).
	 * @throws IOException */
	public String[] GetNames(Names type) throws IOException {
		Reader reader = null;
		String file;
		switch(type) {
			case MALE:		file = "src/handlers/json/mnames.json"; break;
			case FEMALE:	file = "src/handlers/json/fnames.json"; break;
			case SURNAME:	file = "src/handlers/json/snames.json"; break;
			default:		file = "src/handlers/json/snames.json"; break;
		}
		try {
			reader = new FileReader(new File(file));
			return Jason.fromJson(reader, StringList.class).data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if(reader != null) {
				reader.close();
			}
		}
		return null;
	}
	
	/**Gets the list of locations to use when generating ancestor data.
	 * @return			an array of Location objects.*/
	public Location[] GetLocations() {
		Reader reader;
		try {
			reader = new FileReader(new File("src/handlers/json/locations.json"));
			locations = Jason.fromJson(reader, LocationData.class);
			return locations.data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**This class is essentially a struct for the different parts of location information to be read in.*/
	public class Location {
		/**A String with the Location's country.*/
		private String country;
		
		/**A String with the Location's city.*/
		private String city;
		
		/**A double of the Location's latitude.*/
		private double latitude;
		
		/**A double of the Location's longitude.*/
		private double longitude;
		
		
		
		/**@return		the Location's country*/
		public String getCountry() { return country; }
		
		/**@return 		the Location's city*/
		public String getCity() { return city; }
		
		/**@return		the Location's latitude*/
		public double getLatitude() { return latitude; }
		
		/**@return		the Location's longitude*/
		public double getLongitude() { return longitude; }
	}
	
	/**Essentially a struct for an array of Location objects.*/
	public class LocationData {
		/**An array of Location objects.*/
		private Location[] data;
	}
	
	/**A wrapper class for a String[] to allow the JSON conversion for the names.*/
	public class StringList {
		private String[] data;
	}
	
	/**The types of names to be read and converted from JSON files.*/
	public enum Names {
		MALE, FEMALE, SURNAME
	}
}
