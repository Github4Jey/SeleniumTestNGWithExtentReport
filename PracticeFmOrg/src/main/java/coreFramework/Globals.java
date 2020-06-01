package coreFramework;

public class Globals {

	//public static String ConfigFilePath= "D://MyProject2//simpleautomation//src//com//simpleautomation//coreframework//configfile.properties";
	//public static String chromeDriverPath= "D://MyProject2//simpleautomation//Resources//SeleniumDrivers//chromedriver.exe";
	//public static String ieDriverPath= "D://MyProject2//simpleautomation//Resources//SeleniumDrivers//geckodriver.exe";
	//public static String excelFilePath="D://MyProject2//simpleautomation//Resources//Input_Data_POI.xls";
	
	public static String ConfigFilePath= System.getProperty("user.dir")+"\\src\\main\\java\\coreFramework\\configfile.properties";
    public static String chromeDriverPath= System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\SeleniumDrivers\\chromedriver.exe";
    public static String ffDriverPath= System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\SeleniumDrivers\\geckodriver.exe";
    public static String excelFilePath=System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\Test_Scripts_Data.xls";


}


