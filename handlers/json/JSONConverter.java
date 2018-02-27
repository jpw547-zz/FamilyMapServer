package handlers.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import results.*;

import com.google.gson.*;

public class JSONConverter {
//Constructors
	public JSONConverter() {
		Jason = new GsonBuilder().setPrettyPrinting().create();
	}
	
//Data members
	private Gson Jason;
	private LocationData locations;
	
//Remaining class methods
	/***/
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
	
	/**
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
	
	/***/
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
		/**A String with the location's country.*/
		private String country;
		
		/**A String with the location's city.*/
		private String city;
		
		/**A double of the location's latitude.*/
		private double latitude;
		
		/**A double of the location's longitude.*/
		private double longitude;
	}
	
	/**Essentially a struct for an array of Location objects.*/
	public class LocationData {
		/**An array of Location objects.*/
		private Location[] data;
	}
	
	public class StringList {
		private String[] data;
	}
	
	public enum Names {
		MALE, FEMALE, SURNAME
	}
}
