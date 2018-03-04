package tests;

import java.io.IOException;
import java.util.logging.*;

import org.junit.runner.*;
import org.junit.runner.notification.Failure;



public class AllTests {
	  private static Logger logger;

	    static {
	        try {
	            initLog();
	        }
	        catch (IOException e) {
	            System.out.println("Could not initialize log: " + e.getMessage());
				e.printStackTrace();
	        }
	    }
		
	    private static void initLog() throws IOException {

	        Level logLevel = Level.OFF;

	        logger = Logger.getLogger("familymaptest"); 
	        logger.setLevel(logLevel);
	        logger.setUseParentHandlers(false);

	        Handler consoleHandler = new ConsoleHandler();
	        consoleHandler.setLevel(logLevel);
	        consoleHandler.setFormatter(new SimpleFormatter());
	        logger.addHandler(consoleHandler);

	        FileHandler fileHandler = new FileHandler("testlog.txt", false);
	        fileHandler.setLevel(logLevel);
	        fileHandler.setFormatter(new SimpleFormatter());
	        logger.addHandler(fileHandler);
	    }
	    
	 public static void main(String[] args) {
		 //Model tests
		 System.out.println("Starting Model tests...");
		 Result model = JUnitCore.runClasses(tests.model.ModelTests.class);
	     for (Failure failure : model.getFailures()) {
	        System.out.println(failure.toString());
	     }	
	     if(model.wasSuccessful()) { System.out.println("Model tests passed!\n"); }
	     
	     //Dao tests
	     System.out.println("Starting DAO tests...");
		 Result dao = JUnitCore.runClasses(tests.dao.DaoTests.class);
	     for (Failure failure : dao.getFailures()) {
	        System.out.println(failure.toString());
	     }	
	     if(dao.wasSuccessful()) { System.out.println("DAO tests passed!\n"); }
	     
	     //Request tests
	     System.out.println("Starting Request tests...");
		 Result req = JUnitCore.runClasses(tests.requests.RequestTests.class);
	     for (Failure failure : req.getFailures()) {
	        System.out.println(failure.toString());
	     }	
	     if(req.wasSuccessful()) { System.out.println("Request tests passed!\n"); }
	     
	     //Result tests
	     System.out.println("Starting Result tests...");
		 Result res = JUnitCore.runClasses(tests.results.ResultsTests.class);
	     for (Failure failure : res.getFailures()) {
	        System.out.println(failure.toString());
	     }	
	     if(res.wasSuccessful()) { System.out.println("Result tests passed!\n"); }
	     
	     //Service tests
	     System.out.println("Starting Service tests...");
		 Result service = JUnitCore.runClasses(tests.services.ServiceTests.class);
	     for (Failure failure : service.getFailures()) {
	        System.out.println(failure.toString());
	     }	
	     if(service.wasSuccessful()) { System.out.println("Service tests passed!\n"); }
	     
	     //Handler tests
	     System.out.println("Starting Handler tests...");
		 Result handler = JUnitCore.runClasses(tests.handlers.HandlerTests.class);
	     for (Failure failure : handler.getFailures()) {
	        System.out.println(failure.toString());
	     }	
	     if(handler.wasSuccessful()) { System.out.println("Handler tests passed!\n"); }
     }
}
