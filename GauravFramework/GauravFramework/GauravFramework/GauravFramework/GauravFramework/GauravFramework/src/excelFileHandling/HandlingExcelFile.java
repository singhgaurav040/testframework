package excelFileHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HandlingExcelFile {
	
	public static void readingExcels() throws IOException
	{
		File myFile = new File("D:\\Softwares\\SeleniumJarsAndStandalone\\gaurav.xlsx");
		
		FileInputStream fis = new FileInputStream(myFile);
		
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
		
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
				
		Iterator <Row> rowIterator = mySheet.iterator();
		
		while(rowIterator.hasNext())
		{
			Row row = rowIterator.next();
			
			Iterator <Cell> cellIterator = row.iterator();
			
			while(cellIterator.hasNext())
			{
				Cell cell = cellIterator.next();
				
				if(cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
				{
					System.out.println(cell.getStringCellValue()+" ");
				}
				else
				{
					System.out.println(cell.getNumericCellValue()+" ");
				}
			}
			System.out.println();
			
		}
		fis.close();	
		
		
	}
	
	public static void readingParticularCellInExcel(int index,int rownum,int cellnum) throws IOException
	{
		File myFile = new File("D:\\Softwares\\SeleniumJarsAndStandalone\\gaurav1.xlsx");
		FileInputStream fis = new FileInputStream(myFile);
		XSSFWorkbook myWorkbook = new XSSFWorkbook(fis);
		XSSFSheet mySheet = myWorkbook.getSheetAt(index);
		Row row = mySheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);	
	}
	
	public static void writingInExcel() throws IOException
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("javabooks");
		
		XSSFRow row;
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put( "1", new Object[] {"EMP ID", "EMP NAME", "DESIGNATION" });
		data.put("2",new Object[] {"tp01", "Gopal", "Senior Manager"});
		data.put("3",new Object[] {"tp02", "Gaurav", "Module QA Lead"});
		data.put("4",new Object[] {"tp03", "Ankit", "QA Engineer"});
		
		Set<String> keyid = data.keySet();
		int rowid = 0;
		
		for(String key : keyid)
		{
			row= spreadsheet.createRow(rowid++);
			Object[] objarr = data.get(key);
			int cellid=0;
			
			for(Object obj : objarr)
			{
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String)obj);		
			}
		}
		
		FileOutputStream fos = new FileOutputStream(new File("D:\\Softwares\\SeleniumJarsAndStandalone\\javabooks5.xlsx"));
		workbook.write(fos);
		fos.close();
	}
	
	
	public static void writingInParticularCellInExcel(int rownum,int cellnum,String value)
	{
		try{
		File myFile = new File("D:\\Templates\\javabooks.xlsx");
		FileInputStream fis = new FileInputStream(myFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);		
		sheet.createRow(rownum).createCell(cellnum).setCellValue(value);		
		FileOutputStream fout = new FileOutputStream(myFile);
		wb.write(fout);
		wb.close();	
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void updatingExcelSheet() throws IOException
	{
		FileInputStream file = new FileInputStream("D:\\Java 8\\javabooks8.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Cell cell = null;
		
		XSSFRow row = sheet.getRow(2);
		if(row == null)
		{
			row = sheet.createRow(2);			
		}
		
		cell = row.getCell(1);
		if(cell == null)
		{
			cell = row.createCell(1);
		}
		
		cell.setCellValue("Fail");
		
		file.close();
		
		FileOutputStream fout = new FileOutputStream("D:\\Java 8\\javabooks8.xlsx");
		workbook.write(fout);
		fout.close();
		
		
	}
}
