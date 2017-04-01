package jsonFileHandling;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HandlingJsonFile {


	public static void readingJsonFile()
	{
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("D:/WorkspaceJava/gauravframework/GauravFramework/src/Files/jsonFile.json"));
			JSONObject jsonobject = (JSONObject) obj;
			String name = (String) jsonobject.get("Name");
			String author = (String) jsonobject.get("Author");

			JSONArray companylist = (JSONArray) jsonobject.get("Company List");

			System.out.println("Name: " + name);
			System.out.println("Author: " + author);
			System.out.println(companylist);

			Iterator<String> iterator = companylist.iterator();
			while(iterator.hasNext())
			{
				System.out.println(iterator.next());
			}

		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	

	public static void writingJsonFile()
	{
		try
		{
			JSONObject obj = new JSONObject();
			obj.put("Name", "Gaurav");
			obj.put("Author", "Honey");

			JSONArray company = new JSONArray();
			company.add("Company: eBay1");
			company.add("Company: Paypal1");
			company.add("Company: Google1");
			obj.put("Company List", company);

			FileWriter file = new FileWriter("D:/WorkspaceJava/gauravframework/GauravFramework/src/Files/writeInJsonFile.json");
			file.write(obj.toJSONString());
			file.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}



