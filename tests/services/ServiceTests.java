package tests.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	RegisterServiceTest.class,
	ClearServiceTest.class,
	PersonServiceTest.class,
	LoadServiceTest.class,
	EventServiceTest.class,
	LoginServiceTest.class,
	FillServiceTest.class
})
public class ServiceTests {

}
