package coreFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelUtility;
import utilities.ExtentTestManager;
import utilities.UtilityClass;

public class FrameworkBase {
	//ExcelUtility testData = new ExcelUtility(Globals.excelFilePath, "Sheet1", "xls");
	public Map<String, String> map;
	
	//protected ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    //public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
  //  public static ThreadLocal<WebDriver> ThreadDriver=new ThreadLocal<WebDriver>() ;


	public WebDriver driver = null;
	//public String browserName = null;
	public String url = null;
	public ExcelUtility testData = new ExcelUtility(Globals.excelFilePath, "Sheet1", "xls");
	public ExtentTest test;
//	public static ExtentHtmlReporter htmlReporter;
	//public static ExtentReports extent;
	
	//public static ThreadLocal<ExtentTest> ExtentTest=new ThreadLocal<ExtentTest>() ;
    
	//public ExtentTest test;
	public static String overallExecutionStatus = "Passed";
	public String	description;

	public FrameworkBase() {
	
	}
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	@BeforeMethod
	@Parameters({"url","browserName"})  
    public void launchBrowser(Method testMethod, @Optional("http://www.google.com") String url, @Optional("firefox")  String browserName) throws Exception {
	description = getSaltString();
	initalize(url,browserName);
	//	Map<String,String> map;
		String methodname = testMethod.getName();
		System.out.println(methodname);
		String TCDescription = methodname+" description "+browserName;
		
		map= testData.readExcel(methodname);
		test = ExtentTestManager.startTest(TCDescription);
		test.log(Status.INFO, TCDescription);
		
	//	return test;
	}
	
	@AfterMethod
    public void getResult(ITestResult result, Method testMethod) throws IOException
    {
//	test = ExtentTest test;
		String methodname = testMethod.getName();
		String TCDescription = methodname+" description ";
		
		
        if(result.getStatus() == ITestResult.FAILURE)
        {
           // String screenshot_path = UtilityClass.capturescreenshot(driver, result.getName(), TCDescription);
            String screenshot_path = UtilityClass.capturescreenshot(driver,TCDescription);
            test.log(Status.FAIL, result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshot_path).build());
        //    test.log(Status.FAIL, result.getThrowable());
            test.log(Status.INFO, description);
                     
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
            test.log(Status.INFO, description);
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
            test.log(Status.INFO, description);
        }
        
        driver.quit();	
    }
	
	
	
	public void initalize(String url, String browserName) throws Exception {
		

		Properties prop = new Properties();
		FileInputStream fileData = new FileInputStream(Globals.ConfigFilePath);

		prop.load(fileData);
		// Parameters from Properties File
		//browserName = prop.getProperty("browserName");
		//String newurl = prop.getProperty("url");
	//	url = newurl;

		// Load Driver with Selected Browser
		switch (browserName) {
		case "chrome":
		//	System.setProperty("webdriver.chrome.driver", Globals.chromeDriverPath);
			//WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-gpu");
			options.addArguments("test-type");
			// options.addArguments("start-maximized");
			options.addArguments("--enable-automation");
			options.addArguments("test-type=browser");
			options.addArguments("disable-infobars");
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver(options);
			System.out.println(Globals.chromeDriverPath);
			// driver = new ChromeDriver();
			System.out.println("Chrome Browser Opened");
			driver.get(url);
			driverProperties();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Edge Browser Opened");
			driver.get(url);
			driverProperties();
			break;
		case "firefox":
		//	System.setProperty("webdriver.gecko.driver", Globals.ffDriverPath);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			WebDriverManager.firefoxdriver().setup();
			
			 driver = new FirefoxDriver(capabilities); 
			 System.out.println("Firefox Browser Opened");

			driver.get(url);
			driverProperties();
			break;
		default:
			System.setProperty("webdriver.gecko.driver", Globals.ffDriverPath);
			driver = new FirefoxDriver();
			driver.get(url);
			driverProperties();
			break;
		}
	}

	// Set default properties for Driver
	public void driverProperties() {
		// driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		// driver.manage().timeouts().pageLoadTimeout(2,TimeUnit.SECONDS);
	}

	public class BaseTest {
		// map= testData.readExcel("AllocatedToMeCountValidation");

		@BeforeSuite
		public void setUp() throws InterruptedException, IOException {
			//System.out.println("Please wait... 10 seconds checking text file");
		//	Thread.sleep(10000);
			map = testData.readExcel("FrameworkUpdateForCIProcess");
		//	String EmailFolderPath = map.get("EmailFolderPath");
			//String TextFileNameWithExtn = map.get("TextFileNameWithExtn");
			//String FilenameWithFullPath = EmailFolderPath + TextFileNameWithExtn;

	//		File dpsuccestxtfile = new File(FilenameWithFullPath);

			// File dpsuccestxtfile = new
			

		//	if (!dpsuccestxtfile.exists()) {
			//	existingresultfileDelete();
				//throw new SkipException("Skipping the test cases execution due to unavailability of deployment success text file");
			//} else {
				//System.out.println("Deployment success Text File Exists");
				//existingresultfileDelete();
		//	}
			
			//Delete existing result zip file if present
			DeleteResultZipFile.deleteResultZipFile();
			//Delete Final directory from project if exist
			DeleteFinalFolderFromProject.DeleteFinalFolderFromProject();

			// Delete already existing screenshots from screenshot folder
			File file = new File(System.getProperty("user.dir") + "/Screenshot");
			String[] myFiles;
			if (file.isDirectory()) {
				myFiles = file.list();
				for (int i = 0; i < myFiles.length; i++) {
					File myFile = new File(file, myFiles[i]);
					myFile.delete();
				}
			}
					}

		@AfterSuite
		public void tearDown() throws Exception {

			// extent report result update in html
			//extent.flush();

			// Final folder creation to move html file
			String ResultFileNameWithHTMLExtn = map.get("ResultFileNameWithHTMLExtn");

			String workingDir = System.getProperty("user.dir");
			File file = new File(workingDir + "/Final");
			if (!file.exists()) {
				if (file.mkdir()) {
					System.out.println("Final Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}
			// move result file to Final folder
			Path sourcedir = Paths.get(System.getProperty("user.dir") + "/test-output/Functional-Test-Automation-Report.html");//do not modify this line
			Path destdir = Paths.get(System.getProperty("user.dir") + "/Final/" + ResultFileNameWithHTMLExtn);
			java.nio.file.Files.copy(sourcedir, destdir, StandardCopyOption.REPLACE_EXISTING);

			// zip file creation
			String str = null;

			String[] HTMLFilesplit = ResultFileNameWithHTMLExtn.split("\\.");
			String passedResultZipFile = HTMLFilesplit[0] + "_Passed.zip";
			String failedResultZipFile = HTMLFilesplit[0] + "_Failed.zip";

			// String str=System.getProperty("user.dir")+ "\\"
			// +"MyOwnReport-autoscriptexn-pass-fail-script-details.zip";
			if (overallExecutionStatus.contentEquals("Failed")) {
				str = System.getProperty("user.dir") + "\\" + failedResultZipFile;
			} else {
				str = System.getProperty("user.dir") + "\\" + passedResultZipFile;
			}
			File zipFile = new File(str);
			Project p = new Project();
			p.init();
			Zip zip = new Zip();
			zip.setProject(p);
			zip.setBasedir(new File(System.getProperty("user.dir")));
			zip.setIncludes("Final/**");
			zip.setIncludes("Screenshot/**");
			zip.setDestFile(zipFile);
			zip.perform();

		
		}
	}

	public void existingresultfileDelete() {

		// Delete already existing result zip file

		String ResultFileNameWithHTMLExtn = map.get("ResultFileNameWithHTMLExtn");

		String[] HTMLFilesplit = ResultFileNameWithHTMLExtn.split("\\.");
		String passedResultZipFile = HTMLFilesplit[0] + "_Passed.zip";
		String failedResultZipFile = HTMLFilesplit[0] + "_Failed.zip";
		
		
		File failedzip = new File(System.getProperty("user.dir") + "\\"+failedResultZipFile);
		if (!failedzip.exists())
			  System.out.println("Failed Zip Result File Doesn't exist");
			else if (!failedzip.canWrite())
			  System.out.println("No write permission to Failed Zip Result File");
		

		if (failedzip.delete()) {
			System.out.println("Failed Zip Result exists and deleted successfully");
		} else {
			File passedzip = new File(System.getProperty("user.dir")+ "\\"+passedResultZipFile);
			if (!passedzip.exists())
				  System.out.println("Passed Zip Result File Doesn't exist");
				else if (!passedzip.canWrite())
				  System.out.println("No write permission to Passed Zip Result File");
			if (passedzip.delete()) {
				System.out.println("Passed Zip Result exists and deleted successfully");
			}

		}

	}

}
