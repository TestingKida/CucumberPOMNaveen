package com.qa.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	WebDriver driver;

	static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/*This method is used to initilized threadlocal driver on the basis of given browser
	 * @input browser
	 * @return it will return tlDriver
	 * */
	public WebDriver init_driver(String bowser){

		System.out.println("Browser Value is = "+bowser);
		if(bowser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
			//getDriver().get("chrome://settings/clearBrowserData");
			//getDriver().findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
		}else if(bowser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		}else if(bowser.equalsIgnoreCase("safari")){
			tlDriver.set(new SafariDriver());
		}else{
			System.out.println("PLease pass correct browser value = "+bowser);
		}		
		//getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	/**this is used to get Driver with Threadlocal 
	 * @return  "synchronized" keyword is used for parallel execution
	 */
	 public static synchronized WebDriver getDriver(){
		return tlDriver.get();
	 }
}
