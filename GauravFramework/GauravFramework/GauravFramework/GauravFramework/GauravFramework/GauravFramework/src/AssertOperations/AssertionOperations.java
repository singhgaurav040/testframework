package AssertOperations;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ActionOperations.ActionsOpr;


public class AssertionOperations extends ActionsOpr{
	
	public static void stringAssertion(String act,String exp)
	{
		Assert.assertEquals(act, exp);
	}

	public static void numberAssertion(int act,int exp)
	{
		Assert.assertEquals(act, exp);
	}
	
	public static void stringPartialEquality(String text1,String text2)
	{
		Assert.assertTrue(text1.contains(text2));
	}
	
	public static void verifyElementDisplayed(String strElementName)
	{
		int ifExist = ActionsOpr.getElementsCount(strElementName);
		if(ifExist >= 1)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}
	
	public static void verifyElementNotDisplayed(String strElementName)
	{
		int ifExist = ActionsOpr.getElementsCount(strElementName);
		if(ifExist == 0)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}
	}
	
	
	public static void verifyElementEnabled(String strElementName)
	{
		WebElement objElement = ActionsOpr.getElement(strElementName);
		boolean status = objElement.isEnabled();
		Assert.assertTrue(status);
	}
	
	public static void verifyElementNotEnabled(String strElementName)
	{
		WebElement objElement = ActionsOpr.getElement(strElementName);
		boolean status = objElement.isEnabled();
		Assert.assertFalse(status);
	}
	
	public static void verifyElementProperty(String elementName,String property,String expectedval)
	{
		String val = null;
		{
		WebElement element = getElement(elementName);
		val = element.getAttribute(property);
		Assert.assertEquals(val, expectedval);
		}
		
	}
	
	public static int getResponseCode(String urlString) throws MalformedURLException, IOException {
		URL u = new URL(urlString); 
		HttpURLConnection huc =  (HttpURLConnection)  u.openConnection(); 
		huc.setRequestMethod("GET"); 
		huc.connect(); 
		return huc.getResponseCode();
	}
	
	public static void verifyAllLinks()
	{
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		
		for(int i=0;i<=allLinks.size();i++)
		{
			int statuscode = 0;
			try {
				statuscode = getResponseCode(allLinks.get(i).getAttribute("href"));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(statuscode== 200)
			{
				System.out.println("Link is working" +allLinks.get(i).getAttribute("href"));
			}	
			else if(statuscode==400)
			{
				System.out.println("Link not working: Bad Request" +allLinks.get(1).getAttribute("href"));
			}
			else if(statuscode==401)
			{
				System.out.println("Link not working: Unauthorized" +allLinks.get(1).getAttribute("href"));
			}
			else if(statuscode==403)
			{
				System.out.println("Link not working: forbidden" +allLinks.get(1).getAttribute("href"));
			}
			else if(statuscode==404)
			{
				System.out.println("Link not working: Not Found" +allLinks.get(1).getAttribute("href"));
			}
			else if(statuscode==408)
			{
				System.out.println("Link not working: Request Timeout" +allLinks.get(1).getAttribute("href"));
			}
			else if(statuscode==500)
			{
				System.out.println("Link not working: Internal server error" +allLinks.get(i).getAttribute("href"));
			}
			else if(statuscode==503)
			{
				System.out.println("Link not working: Service Unavailable" +allLinks.get(i).getAttribute("href"));
			}
			else if(statuscode==504)
			{
				System.out.println("Link not working: Gateaway Timeout" +allLinks.get(i).getAttribute("href"));
			}
			else
			{
				System.out.println("Something Went Wrong :"+allLinks.get(i).getAttribute("href"));
			}
		}
		
	}
}
