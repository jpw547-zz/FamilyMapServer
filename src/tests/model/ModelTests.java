package tests.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	AuthTokenTest.class, 
	EventTest.class, 
	PersonTest.class,
	UserTest.class })
public class ModelTests {

}
