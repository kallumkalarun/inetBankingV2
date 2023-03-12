package com.inetbanking.testCases;

import java.io.IOException;


import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginTestDDT_003 extends BaseClass {

	@Test(dataProvider="logindata")
	public void LoginTestDDT(String username, String password, String Status) throws InterruptedException {	
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Enter username for DDT Login Test");
		lp.setPassword(password);
		logger.info("Enter password for DDT Login Test");
		lp.clkbtnLogin();
		logger.info("Login Button Clicked for DDT Login Test");
		Thread.sleep(3000);
		
		if(isloginAlertPresent()==true) {
			if(Status.equals("Fail")) {
				//Wrong username/password input login failed- So Test Passed
				Thread.sleep(3000);
				logger.info("Wrong username/password input login failed- So Test Passed");
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				Assert.assertTrue(true);
			}
			else if(Status.equals("Pass")) {
				//Correct username/password input login failed- So Test Failed
				Thread.sleep(3000);
				logger.warn("Correct username/password input login failed- So Test Failed");
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				Assert.assertTrue(false);
			}
		}
		else if(isloginAlertPresent()==false){
			if(Status.equals("Pass")) {
				//Correct username/password input login passed- So Test Passed
				Thread.sleep(3000);
				logger.info("Correct username/password input login passed- So Test Passed");
				lp.clklnkLogout();
				islogoutAlertPresent();
				driver.switchTo().defaultContent();
				Assert.assertTrue(true);
			}
			else if(Status.equals("Fail")) {
				//Wrong username/password input login passed- So Test Failed
				Thread.sleep(3000);
				logger.warn("Wrong username/password input login passed- So Test Failed");
				lp.clklnkLogout();
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				Assert.assertTrue(false);
			}

		}
	}
	
	
	//user defined method created to check login alert is present or not
	public boolean isloginAlertPresent() {
		try {
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}
	}
	
	//user defined method created to check logout alert is present or not
	public boolean islogoutAlertPresent() {
		try {
		driver.switchTo().alert();
		logger.info(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}
	}

	@DataProvider(name="logindata")
	public Object[][] getXLData() {
		//String XLPath = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		String XLPath = "./src/test/java/com/inetbanking/testData/LoginData.xlsx";
		Object logindata[][] = null;
		
		try {
			
			int rownum = XLUtils.getRowCount(XLPath, "Sheet1");
			int colcount=XLUtils.getCellCount(XLPath, "Sheet1", 1);
			
			//Object logindata[][] = {{"aaa","aaa","Fail"},{"aaa","aaa","Fail"}};
			
			
			logindata = new Object[rownum][colcount];
			
			for (int i=1; i<=rownum; i++)
			{
				for (int j=0;j<colcount;j++)
				{
					logindata[i-1][j]= XLUtils.getCellData(XLPath,"Sheet1",i,j);
				}
			}
			
		}
		catch (IOException e){
			logger.error("Exception Error is : ", e);
		}
		return logindata;
		
	}
	
	
	
}
