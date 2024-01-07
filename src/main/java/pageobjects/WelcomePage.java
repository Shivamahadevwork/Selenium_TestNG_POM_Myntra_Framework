package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import page_reusable_components.ReusableFunctions;

public class WelcomePage extends ReusableFunctions{
	
		public WebDriver wd;
		
		public WelcomePage(WebDriver wd) {
		super(wd);
		this.wd = wd;
		PageFactory.initElements(wd, this);			
		}
		
		//Page Elements
		
		@FindBy(xpath="//span[text()='Profile']")
		WebElement Profile;
		
		@FindBy(xpath="//a[@data-track='login']")
		WebElement LoginORSignup;
		
		@FindBy(linkText="Men")
		WebElement Men;
		
		// Page Action Methods
		public void launchApplication() {
		wd.get(prop.getProperty("URL"));	
		}
		
		public void clickProfile() {
		Profile.click();	
		}
		
		public void clickLoginORSignup() {
		LoginORSignup.click();	
		}	
		
		public MenSportShoePage movetoMenMenu() {
		wd.navigate().to("https://www.myntra.com/men-sports-shoes");
		takeScreenshot();
		return new MenSportShoePage(wd);		
		}	

}
