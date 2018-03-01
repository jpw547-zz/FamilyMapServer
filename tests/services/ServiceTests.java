package tests.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	LoginServiceTest.class,
	ClearServiceTest.class,
	LoadServiceTest.class,
	RegisterServiceTest.class,
	PersonServiceTest.class,
	EventServiceTest.class
})
public class ServiceTests {

}
