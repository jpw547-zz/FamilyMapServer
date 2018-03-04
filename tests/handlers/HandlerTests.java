package tests.handlers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	JSONConverterTest.class,
	FileHandlerTest.class,
	ClearHandlerTest.class
})
public class HandlerTests {

}
