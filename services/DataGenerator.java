package services;

import handlers.json.JSONConverter;
import handlers.json.JSONConverter.Location;
import handlers.json.JSONConverter.Names;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.logging.*;

import requests.LoadRequest;

import model.*;

public class DataGenerator {

	public DataGenerator() {
		rand = new Random();
		personList = new ArrayList<Person>();
		eventList = new ArrayList<Event>();
	}
	
	private static Logger logger;
	static { logger = Logger.getLogger("familymaptest"); }
	private static int DEFAULT_GENERATIONS = 4;
	private static int CURRENT_YEAR = 2018;
	private static int MIN_MARRIAGE_AGE = 18;
	private static int MIN_BAPTISM_AGE = 8;
	private static int MAX_PARENT_AGE = 32;
	private static int MAX_AGE = 100;
	
	private int generations = 0;
	private Random rand;
	private String[] men;
	private String[] women;
	private String[] last;
	private Location[] locations;
	private int fatherBirthYear;
	private int motherBirthYear;
	private int fatherBaptismYear;
	private int motherBaptismYear;
	private int marriageYear;
	private ArrayList<Person> personList;
	private ArrayList<Event> eventList;
	
	public LoadRequest GenerateDefaultAncestorData(Person start)   {
		logger.log(Level.INFO, "Starting GenerateDefaultAncestorData.");
		JSONConverter j = new JSONConverter();
		try {
			//Get the list of possible names for the Persons we will generate.
			men = j.GetNames(Names.MALE);
			women = j.GetNames(Names.FEMALE);
			last = j.GetNames(Names.SURNAME);
			locations = j.GetLocations();
		} catch (IOException io) {
			logger.log(Level.SEVERE, io.getLocalizedMessage());
		}
		
		
		//We make an assumption that the average user is between 1 and 30 years old, and use the user's "birth year" to start.
		int childBirthYear = (CURRENT_YEAR - (rand.nextInt(30) + 1));
		generations = DEFAULT_GENERATIONS;
		
		AddNewGeneration(1, start, childBirthYear);
		personList.add(start);
		logger.log(Level.INFO, "Exiting GenerateDefaultAncestorData.");
		Person[] per = new Person[personList.size()];
		Event[] evt = new Event[eventList.size()];
		return new LoadRequest(new User[0], personList.toArray(per), eventList.toArray(evt));
	}
	
	public LoadRequest GenerateAncestorData(Person start, int gen)   {
		logger.log(Level.INFO, "Starting GenerateAncestorData - non default.");
		JSONConverter j = new JSONConverter();
		try {
			//Get the list of possible names for the Persons we will generate.
			men = j.GetNames(Names.MALE);
			women = j.GetNames(Names.FEMALE);
			last = j.GetNames(Names.SURNAME);
			locations = j.GetLocations();
		} catch (IOException io) {
			logger.log(Level.SEVERE, io.getLocalizedMessage());
		}
		
		//We make an assumption that the average user is between 1 and 30 years old, and use the user's "birth year" to start.
		int childBirthYear = (CURRENT_YEAR - (rand.nextInt(30) + 1));
		generations = gen;
		
		AddNewGeneration(1, start, childBirthYear);
		personList.add(start);
		logger.log(Level.INFO, "Exiting GenerateAncestorData - non default.");
		Person[] per = new Person[personList.size()];
		Event[] evt = new Event[eventList.size()];
		return new LoadRequest(null, personList.toArray(per), eventList.toArray(evt));
	}
	
	private void AddNewGeneration(int gen, Person child, int childBirthYear)   {
		logger.log(Level.INFO, "Starting AddNewGeneration.");
		logger.log(Level.FINE, String.format("At new gen start, gen = %s and generations = %s", gen, generations));
		//Exit out of the function if we are past the number of generations that we should create.
		if(gen > generations) { return; }
		
		//Create ID's for the father and mother and assign them to the child.
		String fatherID = UUID.randomUUID().toString();
		String motherID = UUID.randomUUID().toString();
		child.setFather(fatherID);
		child.setMother(motherID);
		
		//Generate a new Person as the father.
		Person father = new Person(fatherID, men[rand.nextInt(men.length)], child.getLastName(), 'M', child.getDescendant(), "", "", motherID);
		//Generate a new Person as the mother.
		Person mother = new Person(motherID, women[rand.nextInt(men.length)], last[rand.nextInt(last.length)], 'F', child.getDescendant(), "", "", fatherID);
		//Insert these Person objects into the database.
		
		GenerateAncestorEvents(father, mother, childBirthYear);
		
		gen++;
		//Create a new generation based off of the father and mother.
		AddNewGeneration(gen, father, fatherBirthYear);
		AddNewGeneration(gen, mother, motherBirthYear);
		
		personList.add(father);
		personList.add(mother);
		
		logger.log(Level.INFO, "Exiting AddNewGeneration.");
	}
	
	private void GenerateAncestorEvents(Person father, Person mother, int childBirthYear)   {
		logger.log(Level.INFO, "Starting GenerateAncestorEvents.");
		GenerateBirthEvents(father, mother, childBirthYear);			
		GenerateBaptismEvents(father, mother);
		GenerateMarriageEvent(father, mother);
		GenerateDeathEvents(father, mother);
		logger.log(Level.INFO, "Exiting GenerateAncestorEvents.");
	}
	
	private void GenerateBirthEvents(Person father, Person mother, int childBirthYear)   {
		logger.log(Level.FINER, "Starting GenerateBirthEvents.");
		//Generate birth events for mother and father.
		
		do {
			//Generate a random year by getting a random number between 0 and 32, adding 18, and subtracting from the child birth year.
			//Keep doing until the father's birth was at least 18 years before the child.
			fatherBirthYear = (childBirthYear - (rand.nextInt(MAX_PARENT_AGE) + MIN_MARRIAGE_AGE)); 
		} while (fatherBirthYear > (childBirthYear - MIN_MARRIAGE_AGE) || fatherBirthYear < (childBirthYear - MAX_PARENT_AGE));
		
		String fBirthID = UUID.randomUUID().toString();
		int fLocationIndex = rand.nextInt(locations.length);
		eventList.add(
				new Event(
						fBirthID,
						father.getPersonID(),
						father.getDescendant(),
						locations[fLocationIndex].getLatitude(),
						locations[fLocationIndex].getLongitude(),
						locations[fLocationIndex].getCountry(),
						locations[fLocationIndex].getCity(),
						"birth",
						Integer.toString(fatherBirthYear)
						));
		
		do {
			//Generate a random year by getting a random number between 0 and 32, adding 18, and subtracting from the child birth year.
			//Keep doing until the mother's birth was at least 18 years before the child.
			motherBirthYear = (childBirthYear - (rand.nextInt(MAX_PARENT_AGE) + MIN_MARRIAGE_AGE)); 
		} while (motherBirthYear > (childBirthYear - MIN_MARRIAGE_AGE) || motherBirthYear < (childBirthYear - MAX_PARENT_AGE));
		
		String mBirthID = UUID.randomUUID().toString();
		int mLocationIndex = rand.nextInt(locations.length);
		eventList.add(
				new Event(
						mBirthID,
						mother.getPersonID(),
						mother.getDescendant(),
						locations[mLocationIndex].getLatitude(),
						locations[mLocationIndex].getLongitude(),
						locations[mLocationIndex].getCountry(),
						locations[mLocationIndex].getCity(),
						"birth",
						Integer.toString(motherBirthYear)
						));
		
		logger.log(Level.FINER, "Exiting GenerateBirthEvents.");
	}
	
	private void GenerateBaptismEvents(Person father, Person mother)   {
		logger.log(Level.FINER, "Starting GenerateBaptismEvents.");
		//Generate baptism events for mother and father.
		
		do {
			fatherBaptismYear = (fatherBirthYear + rand.nextInt(MAX_AGE - MIN_BAPTISM_AGE) + MIN_BAPTISM_AGE);
		} while ((fatherBaptismYear - fatherBirthYear) > MAX_AGE);
		
		String fBaptismID = UUID.randomUUID().toString();
		int fLocationIndex = rand.nextInt(locations.length);
		eventList.add(
				new Event(
						fBaptismID,
						father.getPersonID(),
						father.getDescendant(),
						locations[fLocationIndex].getLatitude(),
						locations[fLocationIndex].getLongitude(),
						locations[fLocationIndex].getCountry(),
						locations[fLocationIndex].getCity(),
						"baptism",
						Integer.toString(fatherBaptismYear)
						));
		
		do {
			motherBaptismYear = (motherBirthYear + rand.nextInt(MAX_AGE - MIN_BAPTISM_AGE) + MIN_BAPTISM_AGE);
		} while ((motherBaptismYear - motherBirthYear) > MAX_AGE);
		
		String mBaptismID = UUID.randomUUID().toString();
		int mLocationIndex = rand.nextInt(locations.length);
		eventList.add(
				new Event(
						mBaptismID,
						mother.getPersonID(),
						mother.getDescendant(),
						locations[mLocationIndex].getLatitude(),
						locations[mLocationIndex].getLongitude(),
						locations[mLocationIndex].getCountry(),
						locations[mLocationIndex].getCity(),
						"baptism",
						Integer.toString(motherBaptismYear)
						));
		
		logger.log(Level.FINER, "Exiting GenerateBaptismEvents.");
	}
	
	private void GenerateMarriageEvent(Person father, Person mother)   {
		logger.log(Level.FINER, "Starting GenerateMarriageEvent.");
		//Generate marriage event.
		
		do {
			marriageYear = (motherBirthYear + rand.nextInt(MAX_AGE - MIN_MARRIAGE_AGE) + MIN_MARRIAGE_AGE);
		} while ((marriageYear - motherBirthYear) > MAX_AGE || (marriageYear - fatherBirthYear) > MAX_AGE);
		
		String fMarriageID = UUID.randomUUID().toString();
		int LocationIndex = rand.nextInt(locations.length);
		eventList.add(
				new Event(
						fMarriageID,
						father.getPersonID(),
						father.getDescendant(),
						locations[LocationIndex].getLatitude(),
						locations[LocationIndex].getLongitude(),
						locations[LocationIndex].getCountry(),
						locations[LocationIndex].getCity(),
						"marriage",
						Integer.toString(marriageYear)
						));
		
		String mMarriageID = UUID.randomUUID().toString();
		eventList.add(
				new Event(
						mMarriageID,
						mother.getPersonID(),
						mother.getDescendant(),
						locations[LocationIndex].getLatitude(),
						locations[LocationIndex].getLongitude(),
						locations[LocationIndex].getCountry(),
						locations[LocationIndex].getCity(),
						"marriage",
						Integer.toString(marriageYear)
						));
		
		logger.log(Level.FINER, "Exiting GenerateMarriageEvent.");
	}

	private void GenerateDeathEvents(Person father, Person mother)   {
		logger.log(Level.FINER, "Starting GenerateDeathEvents.");
		//Generate death events for mother and father.
		
		int fatherDeathYear;
		int motherDeathYear;
		
		int fLastEvent = Math.max(fatherBaptismYear, marriageYear);
		int fYearsTilMax = (MAX_AGE - (fLastEvent - fatherBirthYear) + 1);
		do {
			fatherDeathYear = (fLastEvent + rand.nextInt((fYearsTilMax)));
		} while (fatherDeathYear < fatherBaptismYear || fatherDeathYear < marriageYear);
		
		String fDeathID = UUID.randomUUID().toString();
		int fLocationIndex = rand.nextInt(locations.length);
		eventList.add(
				new Event(
						fDeathID,
						father.getPersonID(),
						father.getDescendant(),
						locations[fLocationIndex].getLatitude(),
						locations[fLocationIndex].getLongitude(),
						locations[fLocationIndex].getCountry(),
						locations[fLocationIndex].getCity(),
						"death",
						Integer.toString(fatherDeathYear)
						));
				
		int mLastEvent = Math.max(motherBaptismYear, marriageYear);
		int mYearsTilMax = (MAX_AGE - (mLastEvent - motherBirthYear) + 1);
		do {
			motherDeathYear = (mLastEvent + rand.nextInt((mYearsTilMax)));
		} while (motherDeathYear < motherBaptismYear || motherDeathYear < marriageYear);
		
		
		String mDeathID = UUID.randomUUID().toString();
		int mLocationIndex = rand.nextInt(locations.length);
		eventList.add(
				new Event(
						mDeathID,
						mother.getPersonID(),
						mother.getDescendant(),
						locations[mLocationIndex].getLatitude(),
						locations[mLocationIndex].getLongitude(),
						locations[mLocationIndex].getCountry(),
						locations[mLocationIndex].getCity(),
						"death",
						Integer.toString(motherDeathYear)
						));
		
		logger.log(Level.FINER, "Exiting GenerateDeathEvents.");
	}
}
