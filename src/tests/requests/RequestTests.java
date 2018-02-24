package tests.requests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	RegisterRequestTest.class,
	PersonRequestTest.class,
	LoginRequestTest.class,
	LoadRequestTest.class,
	FillRequestTest.class,
	EventRequestTest.class
})
public class RequestTests {

}
