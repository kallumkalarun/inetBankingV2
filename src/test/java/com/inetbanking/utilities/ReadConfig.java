package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	// constructor for loading complete file
	public ReadConfig() {
		
		File src=new File("./Configuration/config.properties");
		
		try {
			FileInputStream fls =new FileInputStream(src);
			pro = new Properties();
			pro.load(fls);  // loading complete config.properties file
			
		}catch (Exception e) {
			System.out.println("Exception is :"+ e.getMessage());
		}
		
	}
	
	
	public String getApplicationURL() {
		String url=pro.getProperty("baseURL");
		return url;
	}
	
	public String getUserName() {
		String username=pro.getProperty("username");
		return username;
	}
	
	public String getPassword() {
		String password=pro.getProperty("password");
		return password;
	}
	
	public String getChromePath() {
		String chromepath=pro.getProperty("chromepath");
		return chromepath;
	}
	
	public String getFirefoxPath() {
		String firefoxpath=pro.getProperty("firefoxpath");
		return firefoxpath;
	}
	
	
	
	
	
	
}
