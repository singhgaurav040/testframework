package csvFileHandling;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class csvhandling {

	public static void csvFileReading()
	{
		try {
			CSVReader reader = new CSVReader(new FileReader("My File"));

			String[] nextline;

			try {
				while((nextline = reader.readNext()) != null)
				{
					System.out.println(nextline[0] + nextline[1] + "etc...");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//	public static void csvFileReadAll()
	//	{		
	//		CSVReader reader = new CSVReader(new FileReader("My File"));
	//		List<String[]> allRows = reader.readAll();
	//
	//		for(String[] row : allRows){
	//			System.out.println(Arrays.toString(row));
	//		}

	public static void readParticularCellFromCSV(int columnnumber,int cellvalue)
	{
		try {
			CSVReader reader = new CSVReader(new FileReader("My File"));

			String[] nextline;

			try {
				while((nextline = reader.readNext()) != null)
				{
					String columnno = nextline[columnnumber];
					String[] splitcolumnvalue = columnno.split(",");
					String cellvalue1 = splitcolumnvalue[cellvalue];
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void updatingCSVFile()
	{
		
	}
	
	public static void writingInCSV()
	{
		CSVWriter writer;
		try {
			writer = new CSVWriter(new FileWriter("My File"));
			ArrayList<String[]> data = new ArrayList<String[]>(); 
			data.add(new String[] {"Student","Subject","Marks"});  
			data.add(new String[] {"Hassan","Math","90"});
			writer.writeAll(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	

}
