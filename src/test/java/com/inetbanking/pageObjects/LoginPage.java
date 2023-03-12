package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;
	
	//constructor
	public LoginPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	//Identification of Web Elements from page 
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(name="btnReset")
	@CacheLookup
	WebElement btnReset;
	
	//A[@href='Logout.php'][text()='Log out']
	//*[@attribute='value'
	//a[@href='https://demo.guru99.com/v4/manager/Logout.php']/i

	@FindBy(xpath="//A[@href='Logout.php'][text()='Log out']")
	@CacheLookup
	WebElement lnkLogout;
	
	
	//Action Method for the Elements find out above
	
	public void setUserName(String uname) {
		
		txtUserName.sendKeys(uname);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clkbtnLogin() {
		btnLogin.click(); 
	}
	
	public void clkbtnReset() {
		btnReset.click();
	}
	
	public String gettxtboxUserName() {
		String txtboxUname= txtUserName.getText();
		return txtboxUname;
	}
	
	public String gettxtboxPassword() {
		String txtboxPwd= txtPassword.getText();
		return txtboxPwd;
	}
	
	public void clklnkLogout()
	{
		lnkLogout.click();
	}
	
	}
