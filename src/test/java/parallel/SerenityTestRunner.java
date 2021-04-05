package parallel;

import org.junit.runner.RunWith;

import io.cucumber.testng.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = {"src/test/resources/parallel"}, ///LoginPage.feature
		glue = {"parallel"}, 
		plugin = {"pretty"},
	    	monochrome = true		
		)

public class SerenityTestRunner {

}
