package tests.results;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ResultTest.class,
	PersonResultTest.class,
	EventResultTest.class,
	AuthResultTest.class
})
public class ResultsTests {

}
