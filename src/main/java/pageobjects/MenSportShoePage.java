package pageobjects;

import static java.awt.Point.*;

import java.awt.Point;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;
import page_reusable_components.ReusableFunctions;

public class MenSportShoePage extends ReusableFunctions{
	
		public WebDriver wd;
		Actions act;
		
		public MenSportShoePage(WebDriver wd) {
		super(wd);
		this.wd = wd;
		PageFactory.initElements(wd, this);			
		}
		
		//Page Elements		
		@FindBy(xpath="//input[@value='Puma']")
		WebElement SelectPuma;
					
		@FindBy(xpath="(//input[@type='checkbox' and @class='price-input'])[2]")
		WebElement PriceRange;
		
		@FindBy(xpath="(//img[@draggable='false'])[12]")
		WebElement Product;
		
		@FindBy(xpath="//div[contains(@class, 'pdp-add-to-wishlist') and contains(., 'WISHLIST')]")
		WebElement AddProductToWL;
		
		@FindBy(xpath="//input[@value='50.0 TO 100.0']")
		WebElement DiscountRange;
		
		// Page Action Methods
		@Step
		public Boolean checkPuma() {
		act = new Actions(wd);
		act.moveToElement(SelectPuma).click(SelectPuma).build().perform();
		customWait();
		Boolean isChecked = SelectPuma.isSelected();
		takeScreenshot();
		return isChecked;
		}
		
		@Step
		public Boolean checkPriceRange() {
		act = new Actions(wd);
		act.moveToElement(PriceRange).click(PriceRange).build().perform();
		customWait();
		Boolean isChecked = PriceRange.isSelected();
		takeScreenshot();
		return isChecked;
		}
		
		@Step
		public Boolean checkDiscountRange() {
		act = new Actions(wd);
		act.moveToElement(DiscountRange).click(DiscountRange).build().perform();
		customWait();
		Boolean isChecked = DiscountRange.isSelected();
		takeScreenshot();
		return isChecked;
		}		
		
		@Step
		public void selectProduct() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)wd;
		js.executeScript("window.scrollBy(0,900);");
		takeScreenshot();
		Product.click();
		}
		
		@Step
		public LoginPage addProductToWL() {
		navigateToChildWindow();
		JavascriptExecutor js = (JavascriptExecutor)wd;
		js.executeScript("window.scrollBy(0,300);");
		AddProductToWL.click();
		takeScreenshot();
		return new LoginPage(wd);
		}


}
