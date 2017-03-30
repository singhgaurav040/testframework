package PropertyFileHandling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import BaseClass.baseclassforframework;

public class HandlingPropertiesFile extends baseclassforframework{
	
	public static String getValueFromPropertyFile(String filepath,String key)
	{
		String KeyValue = null;
		Properties prop = new Properties();
		
		FileInputStream propfile;
		try {
			propfile = new FileInputStream(filepath);
			try {
				prop.load(propfile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			KeyValue = prop.getProperty(key);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return KeyValue;
	}

	
}
