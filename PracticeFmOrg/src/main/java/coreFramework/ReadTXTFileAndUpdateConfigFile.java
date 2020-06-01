package coreFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

import utilities.ExcelUtility;

public class ReadTXTFileAndUpdateConfigFile {
	static ExcelUtility testData = new ExcelUtility(Globals.excelFilePath, "Sheet1", "xls");
	static Map<String, String> map;

	public static void readTextAndUpdateConfigFile() throws IOException {

		map = testData.readExcel("FrameworkUpdateForCIProcess");
		String EmailFolderPath = map.get("EmailFolderPath");
		String TextFileNameWithExtn = map.get("TextFileNameWithExtn");
		String FilenameWithFullPath = EmailFolderPath + TextFileNameWithExtn;

		File dpsuccestxtfile = new File(FilenameWithFullPath);

	
		String content;

		content = new String(Files.readAllBytes(Paths.get(FilenameWithFullPath)));

		System.out.println(content);
		// System.out.println( urltobeupdated);
		String filename = System.getProperty("user.dir") + "\\src\\main\\java\\coreframework\\configfile.properties";
		FileInputStream in = new FileInputStream(filename);
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream(filename);
		props.setProperty("url", content);
		props.store(out, null);
		out.close();

	}

}
