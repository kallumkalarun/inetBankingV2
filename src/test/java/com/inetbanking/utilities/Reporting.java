package com.inetbanking.utilities;

import java.io.File;

//Listener class used to generate Extent reports

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking.testCases.BaseClass;

public class Reporting extends TestListenerAdapter
{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	

	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location of the html report
		//sparkReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
				 
		extent=new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","Arun");
		
		
		sparkReporter.config().setDocumentTitle("InetBanking Test Project"); // Tile of report
		sparkReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		//sparkReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		sparkReporter.config().setTheme(Theme.DARK);
		
	
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
	}
	

	public void onTestFailure(ITestResult tr)
	{
	
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the failed information to the report with RED color highlighted

		// Creating Base64 screenshot directly here using  getBase64() Method 
		logger.fail("View Screenshot:", MediaEntityBuilder.createScreenCaptureFromBase64String(BaseClass.getBase64()).build());	
		
	 /*
    	//Used for attaching screenshot to the report from Screenshot folder - we can replace tgi scinario with Base64 image capture
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath); 
		if(f.exists())
		{
		// For directly attach Screenshot from 'Screenshot' Folder 
		logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		
		}
		*/
		

		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	
}
