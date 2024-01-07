package pageobjects;

import java.sql.SQLException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;
import page_reusable_components.ReusableFunctions;

public class LoginPage extends ReusableFunctions{
	
		public WebDriver wd;
		
		public LoginPage(WebDriver wd) {
		super(wd);
		this.wd = wd;
		PageFactory.initElements(wd, this);			
		}
		
		//Page Elements
		
		@FindBy(xpath="//input[@autocomplete='new-password']")
		WebElement Login;
		
		@FindBy(xpath="//div[text()='CONTINUE']")
		WebElement Continue;
		
		@FindBy(xpath="//*[@id=\"sec-if-cpt-message\"]/span")
		WebElement postLoginBanner;
		
		// Page Action Methods
		@Step
		public void clickLogin() throws SQLException {
		Login.sendKeys(extractValuesFromDB("userid"));	
		takeScreenshot();
		}	
		@Step
		public void clickContinue() {
		Continue.click();	
		takeScreenshot();
		}
		
		@Step
		public Boolean verifyPostLoginBanner() {
		wd.switchTo().frame("sec-text-if");
		Boolean bannerDisplayStatus = postLoginBanner.isDisplayed();
		takeScreenshot();
		return bannerDisplayStatus;
		}

}
