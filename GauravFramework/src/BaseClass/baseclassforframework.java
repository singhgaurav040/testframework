package BaseClass;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;

import ActionOperations.ActionsOpr;
import PropertyFileHandling.HandlingPropertiesFile;
import constants.ConstantFilePath;

public class baseclassforframework {
	
	public static WebDriver driver = null;
	public static String configFilePath = null;
	public static String locatorFilePath = null;
	public static String dataFilePath = null;

	@BeforeSuite
	public static void setUp()
	{
		setConfigPath();
		setLocatorPath();
		setDataPath();
		setWebdriver(HandlingPropertiesFile.getValueFromPropertyFile(configFilePath, "browserName"));	
		ActionsOpr.setGlobalTimeout(10, 5);
		ActionsOpr.openApplicationUrl(HandlingPropertiesFile.getValueFromPropertyFile(configFilePath, "baseUrl"));	
		ActionsOpr.windowMaximize();	
	}
	
	public static void setWebdriver(String browserName)
	{
		if(browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff"))
		{
			driver = new FirefoxDriver();
		}
		
		if(browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("ch"))
		{
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
			driver = new ChromeDriver();			
		}
		
		if(browserName.equalsIgnoreCase("internet explorer") || browserName.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
	}
	
	public static WebDriver getWebdriver()
	{
		return driver;
	}
	
	public static WebElement getElement(String elementName) {
		String locatorValue = null;
	    String [] locatorType = null;
		WebElement element=null;
		locatorValue = HandlingPropertiesFile.getValueFromPropertyFile(locatorFilePath, elementName);
		if(elementName.contains("_"))
		{
			locatorType = elementName.split("_");	
		}
		else
		{
			Assert.fail("locator type for "+elementName+" not found in elementrepository file");
		}

		if (locatorType[1].toLowerCase().equals("id")){
			
				element = driver.findElement(By.id(locatorValue));		
		}
		else if(locatorType[1].toLowerCase().equals("name"))
		{
			element = driver.findElement(By.name(locatorValue));
		}
		else if (locatorType[1].toLowerCase().equals("xpath")){
			
			element = driver.findElement(By.xpath(locatorValue));		
		}
		else if(locatorType[1].toLowerCase().equals("css"))
		{
			element = driver.findElement(By.cssSelector(locatorValue));
		}
		else if(locatorType[1].toLowerCase().equals("classname"))
		{
			element = driver.findElement(By.className(locatorValue));
		}
		else if(locatorType[1].toLowerCase().equals("linktext"))
		{
			element = driver.findElement(By.linkText(locatorValue));
		}
		else if(locatorType[1].toLowerCase().equals("tagname"))
		{
			element = driver.findElement(By.tagName(locatorValue));
		}
		else if(locatorType[1].toLowerCase().equals("partiallinktext"))
		{
			element = driver.findElement(By.partialLinkText(locatorValue));
		}
		
		return element;
	}
	
	public static List<WebElement> getElements(String elementName)
	{
		String locatorvalue = null;
		String[] locatortype = null;
		List<WebElement> element = null;
		locatorvalue = HandlingPropertiesFile.getValueFromPropertyFile(locatorFilePath, elementName);
		if(elementName.contains("_"))
		{
			elementName.split("_");
		}
		else
		{
			Assert.fail("locator type for "+elementName+" not found in elementrepository file");
		}
		if(locatortype[1].toLowerCase().equals("id"))
		{
			element = driver.findElements(By.id(locatorvalue));
		}
		else if(locatortype[1].toLowerCase().equals("name"))
		{
			element = driver.findElements(By.name(locatorvalue));
		}
		
		else if(locatortype[1].toLowerCase().equals("xpath"))
		{
			element = driver.findElements(By.xpath(locatorvalue));
		}
		
		else if(locatortype[1].toLowerCase().equals("css"))
		{
			element = driver.findElements(By.cssSelector(locatorvalue));
		}
		else if(locatortype[1].toLowerCase().equals("classname"))
		{
			element = driver.findElements(By.className(locatorvalue));
		}
		
		else if(locatortype[1].toLowerCase().equals("linktext"))
		{
			element = driver.findElements(By.linkText(locatorvalue));
		}
		else if(locatortype[1].toLowerCase().equals("partiallinktext"))
		{
			element = driver.findElements(By.partialLinkText(locatorvalue));
		}
		else if(locatortype[1].toLowerCase().equals("tagname"))
		{
			element = driver.findElements(By.tagName(elementName));
		}
		return element;
	}
	
	public static void closeWindow()
	{
		driver.close();
	}
	
	public static void tearDown()
	{
		driver.quit();
	}
	
	public static void setConfigPath()
	{
		configFilePath = ConstantFilePath.CONFIG_PATH;	
	}
	
	public static void setLocatorPath()
	{
		locatorFilePath = ConstantFilePath.LOCATORS_PATH;
	}
	
	public static void setDataPath()
	{
		dataFilePath = ConstantFilePath.DATA_PATH;
	}
}
