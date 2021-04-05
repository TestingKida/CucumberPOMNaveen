package parallel;

import java.io.File;
import java.util.Properties;

import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.Utils.ConfigReader;
import com.qa.Factory.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/*
 *This class will act as preconditon like before and after methods hook called for ech scenarios
 */
public class ApplicationHooks {

	private WebDriver driver;
	private Properties prop;
	private DriverFactory driverFactory;
	private ConfigReader configReader;
	/* before hook order will executed like 0,1,2 and in After hook it will execute 3,2,1,0 reverse order */

	/*@Before(value="@Skip_Scenario",order = 0)
	public void skip_Scanrio(Scenario scenario){
		System.out.println("SKIPPED SCENARIO : = "+scenario.getName());
		 Assume.assumeTrue(false);
	}*/  // This method help to skip scenarios with tag value
	
	@Before(order = 1)
	public void getProperty(){
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}
	@Before(order = 1)
	public void launchBrowser(){
		driverFactory = new DriverFactory();
		String browserName = prop.getProperty("browser");
		driver = driverFactory.init_driver(browserName);
	}

	@After(order = 0)
	public void quitBrowser(){
		driver.quit();
	}
	@After(order = 1) // this after hook execute before quitbrowser
	public void tearDown(Scenario scenario){
		if(scenario.isFailed()){
			// take screenshot
			String ScreenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath =((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", ScreenshotName);	
		}
		
	}
}
