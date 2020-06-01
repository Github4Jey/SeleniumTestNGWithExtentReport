package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;


public class ExcelUtility {

	private String filePath, sheetName, fileFormat;
	private Workbook workbook;
	private Map<String,String> testDataMap ;
	public int actualRowCount=0;

	public ExcelUtility(String filepath, String sheetName, String fileFormat) {
		this.filePath = filepath;
		this.sheetName = sheetName;
		this.fileFormat= fileFormat;
	}

	public Map<String,String> readExcel(String testCaseName) throws IOException
	{   
		testDataMap = new HashMap<String,String>();

		File file = new File(filePath);
		FileInputStream testDataFile = new FileInputStream(file);

		if (fileFormat.equals("xls")){
			workbook = new HSSFWorkbook(testDataFile);
		}
		else if (fileFormat.equals("xlsx"))
		{
			workbook = new XSSFWorkbook(testDataFile);
		}
		Sheet sheet= workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		for (int i=0; i<=rowCount;i++)
		{
			Row row=sheet.getRow(i);

			DataFormatter formatter = new DataFormatter(); 
			Cell cell = sheet.getRow(i).getCell(0);
			String str = formatter.formatCellValue(cell); 
			if(testCaseName.contentEquals(str)){
				for (int j = 1; j < row.getLastCellNum(); j=j+2) {
					testDataMap.put(formatter.formatCellValue(sheet.getRow(i).getCell(j)),formatter.formatCellValue(sheet.getRow(i).getCell(j+1)));
					actualRowCount = 0;
				}
			}
			else
			{
				actualRowCount=actualRowCount+1;
			}
		}

		if(actualRowCount==rowCount)
		{
			Assert.fail("Test Data is not found for the testCase");			
		}
		return testDataMap;
	}
	
	private Exception Exception;
	public String getValueFromMap(Map<String, String> map, String key)
	{
	String value = null;
	try{
	if(map.containsKey(key)){
	value=map.get(key);
	} else
	{
	throw Exception;
	} 
	} 
	catch(Exception e)
	{
	Assert.fail(key+" is not found in the Test Data row"); 
	}
	return value;
	}


}

