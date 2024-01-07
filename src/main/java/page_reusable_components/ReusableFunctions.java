package page_reusable_components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;

public class ReusableFunctions {
	
	public WebDriver wd;
	public Properties prop;
	
	public ReusableFunctions(WebDriver wd) {
	this.wd = wd;
	prop = new Properties();
	try {
		BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"//src//test//resources//Global_Values.properties"));
		prop.load(reader);
	} catch (IOException e) {
			  Allure.step("Alert - encountered an problem while working with the Properties file");
	}
	}	
	
	public void navigateToChildWindow() {
	Set<String> windowList = wd.getWindowHandles();	
	Iterator<String> ir = windowList.iterator();
	
	String parentWindow = ir.next();
	String childWindow = ir.next();
	
	wd.switchTo().window(childWindow);	
	}
	
	public void customWait() {
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}	
	}
	
	public void takeScreenshot() {
	Date d = new Date();	
	String screenShotFileFormat = d.toString().replace(":", "_").replace(":", "_")+".jpg";
	File screenShotFile = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(screenShotFile, new File(System.getProperty("user.dir")+"//Screenshots", screenShotFileFormat));
	} catch (IOException e) {
		Allure.step("Alert = encountered an error while storing the screenshot to a new file!");
	}
		
	}
	
	public String extractValuesFromDB(String fieldName) throws SQLException {
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myntra", "root", "crAk7abc");	
	Statement connectLayer = connection.createStatement();
	ResultSet resultArray = connectLayer.executeQuery("select * from userInfo;");
	resultArray.next();
	return resultArray.getString(fieldName);	
	}

}
