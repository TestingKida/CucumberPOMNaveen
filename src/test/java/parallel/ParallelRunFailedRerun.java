package parallel;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"@target/failedRerun.txt"}, // also features files are under parallel package
		glue = {"parallel"}, // both stpdef and hooks are in same "parallel" package
		plugin = {"pretty",
				"rerun:target/failedRerun.txt",// this rerun plugin collect failed Scenario in mention ttxt file
													//which we will invoke from different runner
		},
		//tags="not @Skip_Scenario",
	    	monochrome = true		
		)

public class ParallelRunFailedRerun extends AbstractTestNGCucumberTests{
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios(){
		return super.scenarios();
	}

}
