package aclass;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import ActionOperations.ActionsOpr;
import BaseClass.baseclassforframework;
import PropertyFileHandling.HandlingPropertiesFile;

public class BasicScript extends ActionsOpr{
	
	@Test
	public static void login()
	{
		//System.out.println("cool work");
		ActionsOpr.pause(2);
		//ActionsOpr.clean(driver.findElement(By.id(HandlingPropertiesFile.getValueFromPropertyFile(locatorFilePath,))));
		typeText("emailField_id","emailFieldData");
		
		//ActionsOpr.clean(driver.findElement(By.id(HandlingPropertiesFile.getValueFromPropertyFile(filepath, key))));
		//ActionsOpr.typeText(element, value);
		
	}

}
