package tests;

import org.junit.runner.*;
import org.junit.runner.notification.Failure;



public class AllTests {
	 public static void main(String[] args) {
		 //Model tests
		 Result model = JUnitCore.runClasses(tests.model.ModelTests.class);
	     for (Failure failure : model.getFailures()) {
	        System.out.println(failure.toString());
	     }	
	     if(model.wasSuccessful()) { System.out.println("Model tests passed."); }
	     
	     //Dao tests
		 Result dao = JUnitCore.runClasses(tests.dao.DaoTests.class);
	     for (Failure failure : dao.getFailures()) {
	        System.out.println(failure.toString());
	     }	
	     if(dao.wasSuccessful()) { System.out.println("DAO tests passed."); }
     }
}
