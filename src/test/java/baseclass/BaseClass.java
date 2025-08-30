package baseclass;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import utilities.ConfigReader;

public class BaseClass {
	protected WebDriver driver;
	protected ConfigReader configReader;
	protected Properties prop;
	protected String filePath;
	
	@BeforeClass
	public void setup() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();// Load properties into prop
		filePath = prop.getProperty("testDataPath");
	}
	
	@BeforeMethod
	public void launchAppln() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}

}
