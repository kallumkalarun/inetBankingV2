package com.inetbanking.testCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_ResetTest_002 extends BaseClass {

	@Test
	public void ResetTEst() {
		//driver.get(baseURL);
		//logger.info("URL opened for Reset Test");
		
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Enter username for Reset Test");
		lp.setPassword(password);
		logger.info("Enter password for Reset Test");
		lp.clkbtnReset();
		logger.info("Reset Button Clicked for Reset Test");
		
		if(lp.gettxtboxUserName().equals("")&& lp.gettxtboxPassword().equals("")) {
			Assert.assertTrue(true);
			logger.info("Reset Test Passed");
		}
		else {
			Assert.assertTrue(false);
			logger.info("Reset Test Failed");
		}
	}
	
}
