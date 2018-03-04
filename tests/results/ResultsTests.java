package tests.results;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	AuthResultTest.class, 
	EventResultTest.class,
	ListResultTest.class,
	PersonResultTest.class,
	ResultTest.class
})
public class ResultsTests {

}
