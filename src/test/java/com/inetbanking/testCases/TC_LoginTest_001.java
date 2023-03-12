package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	
	@Test
	public void LoginTest() throws IOException, InterruptedException  {
		
		
		//driver.get(baseURL);
		//logger.info("URL opened for Login Test");
		LoginPage lp = new LoginPage(driver);
		 
		lp.setUserName(username);
		logger.info("Enter username for Login Test");
		lp.setPassword(password);
		logger.info("Enter password for Login Test");
		lp.clkbtnLogin();
		logger.info("Login Button Clicked for Login Test");
		Thread.sleep(3000);
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login Test Passed");
		}
		else
		{
			//Method used to take ScreenShots for failed test cases in png format
			//captureScreen(driver,"LoginTest");  // Method available in Base class -- we can use this method insted of Base64 image capture
		
			Assert.assertTrue(false);
			logger.info("Login Test Failed");
			
		}
	}
	
}
