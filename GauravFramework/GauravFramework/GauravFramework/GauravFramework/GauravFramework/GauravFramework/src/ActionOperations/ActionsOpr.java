package ActionOperations;

import org.openqa.selenium.WebDriver.Window;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.History;
import com.gargoylesoftware.htmlunit.javascript.host.Location;

import BaseClass.baseclassforframework;
import PropertyFileHandling.HandlingPropertiesFile;

public class ActionsOpr extends baseclassforframework{

	public static String parent_window;

	public static void clickElement(String elementName)
	{
		WebElement element = getElement(elementName);
		element.click();
	}

	public static void typeText(String elementName,String value)
	{ 
		WebElement element = getElement(elementName);
	    String dataValue = HandlingPropertiesFile.getValueFromPropertyFile(dataFilePath, value);
		element.sendKeys(dataValue);
	}

	public static void clean(String elementName)
	{
		WebElement element = getElement(elementName);
		element.clear();
	}

	public static String getCompleteText(WebElement element)
	{
		String gettingText = element.getText();
		return gettingText;
	}

	public String getCompleteValue(WebElement element)
	{
		String gettingValue = element.getAttribute("value");
		return gettingValue;
	}

	public void browserRefresh()
	{
		driver.navigate().refresh();
	}

	public void goBack()
	{
		driver.navigate().back();
	}

	public static void windowMaximize()
	{
		driver.manage().window().maximize();
	}

	public static void pause(long seconds)
	{
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void waitUntilElementVisible(String elementName,int waittime)
	{
		WebElement element = getElement(elementName);
		WebDriverWait wait = new WebDriverWait(driver,waittime);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitUntilElementClickable(String elementName,int waittime)
	{
		WebElement element = getElement(elementName);
		WebDriverWait wait = new WebDriverWait(driver,waittime);
		wait.until(ExpectedConditions.elementToBeClickable(element));		
	}
	
	public static void verifyAlertPopUpMessage(String message)
	{
		Alert myAlert = driver.switchTo().alert();
		String gettingText = myAlert.getText();
		Assert.assertEquals(gettingText, message);
		driver.switchTo().defaultContent();		
	}

	public static void setGlobalTimeout(int browserLoadTimeout,int pageLoadTime)
	{
		driver.manage().timeouts().pageLoadTimeout(browserLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(pageLoadTime, TimeUnit.SECONDS);
	}

	public static void openApplicationUrl(String baseUrl){

		driver.get(baseUrl);
	}
	public static int randNum()
	{
		Random random = new Random();
		int randomNum;
		randomNum = random.nextInt();
		return randomNum;		
	}

	public static int getElementsCount(String elementName)
	{	
		List<WebElement> element = getElements(elementName);
		int elementscounting = element.size();
		return elementscounting;
	}

	// This will cut starting characters
	public static String textAfterSubstring(WebElement element,int substringValue)
	{
		String substr = getCompleteText(element);
		String latestText = substr.substring(substringValue);
		return latestText;
	}

	// This will give you range which all text I need to display
	public static String rangeSubstring(WebElement element,int startingIndex,int lastIndex)
	{
		String substr1 = getCompleteText(element);
		String substr2 = substr1.substring(startingIndex, lastIndex);
		return substr2;
	}


	public static int findingIndex(WebElement element,String value)
	{
		String finalIndexValue = getCompleteText(element);
		int index = finalIndexValue.indexOf(value);
		return index;
	}
	
	public static String getFilePath()
	{
		String path ="";
		File file = new File("");
		String absoluteFilePath = file.getAbsolutePath();
		path = absoluteFilePath.replaceAll("\\\\+", "/");
		return path;
		
	}

	public static String takeScreenshot(String name)
	{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = getFilePath();
		
		try {
			FileUtils.copyFile(srcFile, new File("results/screenshots/" + name +".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path+"/results/screenshots/" + name +".png";
	}

	public static String gettingDateAndTime()
	{
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");	
		String DateAndTime = dateFormat.format(currentDate);
		return DateAndTime;			
	}

	public static void dragAndDropAction(WebElement sourceElement,WebElement destinationElement)
	{
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, destinationElement).build().perform();
	}

	public static void mouseHoverOnElement(WebElement menu,WebElement submenu)
	{
		Actions action = new Actions(driver);
		action.moveToElement(menu).perform();
		pause(2);
		action.click(submenu);
	}

	public static void selectByTextFromdropDown(WebElement element,String textValue)
	{
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(textValue);
	}

	public static void selectByIndexFromdropDown(WebElement element,int numValue)
	{
		Select dropdown = new Select(element);
		dropdown.selectByIndex(numValue);
	}

	public static void robotClassToPressEnterKey()
	{
		try {
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 	
	}

	public static void multipleWindowHandling(WebElement element)
	{
		parent_window = driver.getWindowHandle();
		element.click();

		for(String child_window : driver.getWindowHandles())
		{
			driver.switchTo().window(child_window);		
		}	
	}

	public static void backToParentWindow()
	{
		driver.close();
		driver.switchTo().window(parent_window);
	}
	
	public static void openNewTab()
	{
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void multipleTabHandling(int tabName)
	{
		ArrayList<String> openTabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(openTabs.get(tabName));
	}

	public static void backToParentTab(int parent_tab)
	{
		ArrayList<String> openTabs = new ArrayList<String> (driver.getWindowHandles());
		driver.close();
		driver.switchTo().window(openTabs.get(parent_tab));
	}
	
	public static void acceptingAlert()
	{
		Alert myAlert = driver.switchTo().alert();
		myAlert.accept();
	}

	public static void dismissAlert()
	{
		Alert myAlert = driver.switchTo().alert();
		myAlert.dismiss();
	}
	
}
