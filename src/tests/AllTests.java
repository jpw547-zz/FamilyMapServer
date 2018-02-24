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
	     
	     //Request tests
		 Result req = JUnitCore.runClasses(tests.requests.RequestTests.class);
	     for (Failure failure : req.getFailures()) {
	        System.out.println(failure.toString());
	     }	
	     if(req.wasSuccessful()) { System.out.println("Request tests passed."); }
	     
	     //Result tests
		 Result res = JUnitCore.runClasses(tests.results.ResultsTests.class);
	     for (Failure failure : res.getFailures()) {
	        System.out.println(failure.toString());
	     }	
	     if(res.wasSuccessful()) { System.out.println("Result tests passed."); }
	     
	     //Service tests
		 Result service = JUnitCore.runClasses(tests.services.ServiceTests.class);
	     for (Failure failure : service.getFailures()) {
	        System.out.println(failure.toString());
	     }	
	     if(service.wasSuccessful()) { System.out.println("Service tests passed."); }
     }
}
