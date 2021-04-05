package parallel;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/parallel/ContactUs.feature"}, // /LoginPage.feature also features files are under parallel package
		glue = {"parallel"}, // both stpdef and hooks are in same "parallel" package
		plugin = {"pretty",
				//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/",
				"rerun:target/failedRerun.txt"// this rerun plugin collect failed Scenario in mention ttxt file
											  //which we will onvike from different runner
		},
		//tags="not @Skip",  // using this tag we can skip scenarios with tags mention in feature file/ other way is from maven termical & from Before hook
	    	monochrome = true		
		)

public class ParallelRun extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		return super.scenarios();
	}

}
