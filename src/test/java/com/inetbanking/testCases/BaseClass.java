package com.inetbanking.testCases;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {

	 ReadConfig readconfig=new ReadConfig();  // read values from 'config.properties' file through 'ReadConfig' class
	 
	 //public String baseURL = "http://demo.guru99.com/v4/index.php";
	 public String baseURL = readconfig.getApplicationURL();
	 //public String username = "mngr480239";
	 public String username = readconfig.getUserName();
	 //public String password = "adyrUju";
	 public String password = readconfig.getPassword();
	
	 public static WebDriver driver;
	 public static Logger logger;
	// public String driverName;
	 

	@Parameters("browser")
	@BeforeClass
	 public void setup(String br) {
		
		// logger initialization for creating logs.
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		//driverName = br;  // for getting driver value in Reporting.java
		if (br.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"//Drivers//chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath()); 
			driver =new ChromeDriver();	
			driver.manage().window().maximize();
			logger.info("Initializing Chrome as Test Browser");
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.geco.driver",readconfig.getFirefoxPath()); 
			driver =new FirefoxDriver();
			driver.manage().window().maximize();
			logger.info("Initializing Firefox as Test Browser");
		}
		
		driver.get(baseURL);
		logger.info("URL opened for Login Test");

	}
	 
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	// Method used to take ScreenShots for failed test cases in png format
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot captured");
	}
	
	/*
	// Method used to take ScreenShots for failed test cases in Base64 format - At present not using this method
	public String captureScreenAsBase64(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		byte[] imageBytes=IOUtils.toByteArray(new FileInputStream(target));
		return Base64.getEncoder().encodeToString(imageBytes);
	}
	
	*/
	
		// Method for Creating Base64 screenshot directly 
	public static String getBase64() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
	

	
	
}
