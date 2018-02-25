package tests.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	AuthDAOTest.class,
	EventDAOTest.class,
	PersonDAOTest.class,
	UserDAOTest.class
})
public class DaoTests {

}
