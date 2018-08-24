package unitTest;
 
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.*;

@RunWith(Cucumber.class) 
@CucumberOptions(features="src/test/java/unitTest",format = {"pretty", "html:target/cucumber"},glue = "stepDefinition") 

public class runTest { }