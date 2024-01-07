package test_reusable_components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import io.qameta.allure.Allure;
import pageobjects.MenSportShoePage;
import pageobjects.WelcomePage;

public class BaseTest {
	
		public WebDriver wd;
		public Properties prop;
		public SoftAssert softAssert;
		
		public WebDriver openBrowser() {
		prop = new Properties();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"//src//test//resources//Global_Values.properties"));
			prop.load(reader);
		} catch (IOException e) {
				  Allure.step("Alert - encountered an problem while working with the Properties file");
		}
		if(prop.getProperty("Browser").equalsIgnoreCase("Firefox") && prop.getProperty("GRID").equalsIgnoreCase("Y")) {
			FirefoxOptions fops = new FirefoxOptions();
			fops.addArguments("--disable-notifications", "--private", "--disable-popup-blocking", "acceptInsecureCerts");
			wd = new FirefoxDriver(fops);
		} else if(prop.getProperty("Browser").equalsIgnoreCase("Chrome") && prop.getProperty("GRID").equalsIgnoreCase("Y")) {
			ChromeOptions cops = new ChromeOptions();
			cops.addArguments("--disable-notifications", "--private", "--disable-popup-blocking", "acceptInsecureCerts");
			wd = new ChromeDriver(cops);			
		} else if(prop.getProperty("Browser").equalsIgnoreCase("Edge") && prop.getProperty("GRID").equalsIgnoreCase("Y")) {
			EdgeOptions eops = new EdgeOptions();
			eops.addArguments("--disable-notifications", "--private", "--disable-popup-blocking", "acceptInsecureCerts");
			wd = new EdgeDriver(eops);
			
		} else {
			if(prop.getProperty("Browser").equalsIgnoreCase("Firefox")){
				FirefoxOptions fops = new FirefoxOptions();
				fops.addArguments("--disable-notifications", "--private", "--disable-popup-blocking", "acceptInsecureCerts");
				wd = new FirefoxDriver(fops);
			} else if(prop.getProperty("Browser").equalsIgnoreCase("Chrome")){
				ChromeOptions cops = new ChromeOptions();
				cops.addArguments("--disable-notifications", "--private", "--disable-popup-blocking", "acceptInsecureCerts");
				wd = new ChromeDriver(cops);				
			} else if(prop.getProperty("Browser").equalsIgnoreCase("Edge")){
				EdgeOptions eops = new EdgeOptions();
				eops.addArguments("--disable-notifications", "--private", "--disable-popup-blocking", "acceptInsecureCerts");
				wd = new EdgeDriver(eops);
			}
		}	
			
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return wd;
		}		
		
		@BeforeMethod
		public void setUpProcesses(ITestResult result) throws Exception {
		softAssert = new SoftAssert();
		String methodName = result.getMethod().getMethodName();
		ScreenRecorderUtil.startRecord(methodName);
		wd = openBrowser();
		WelcomePage wp = new WelcomePage(wd);
		wp.launchApplication();
		MenSportShoePage mssp = wp.movetoMenMenu();
		}
		
		@AfterMethod
		public void tearDownProcesses() throws Exception {
		wd.quit();
		ScreenRecorderUtil.stopRecord();
		}
		
		public void customWait() throws InterruptedException {
		Thread.sleep(2000);	
		}
		

}
