package functional_tests;

import java.sql.SQLException;

import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pageobjects.LoginPage;
import pageobjects.MenSportShoePage;
import test_reusable_components.BaseTest;
import test_reusable_components.RetryAnalyzer;

public class AddProdToWLTest extends BaseTest{
	
		public MenSportShoePage mssp;		
		
		@Description("Test if the user is able to browse a product by using desired filters and add the product to their Wishlist successfully")
		@Test(description = "Validate adding a Product to Wishlist", retryAnalyzer = RetryAnalyzer.class)
		public void validateAddingProdToWL() throws InterruptedException, SQLException {
		mssp = new MenSportShoePage(wd);
		Boolean isBrandChecked = mssp.checkPuma();
		if(isBrandChecked) {
		Allure.step("Brand Checkbox selected successfully!");
		} else {
		softAssert.fail("Alert - encountered an issue while selecting the Brand checkbox!");
		}
		customWait();
		Boolean isDiscountRangeChecked = mssp.checkDiscountRange();
		if(isDiscountRangeChecked) {
		Allure.step("Discount Range selected successfully!");
		} else {
		softAssert.fail("Alert - encountered an issue while selecting the Discount Range radio button!");
		}
		customWait();
		Boolean isPriceRangeChecked = mssp.checkPriceRange();
		if(isPriceRangeChecked) {
		Allure.step("Price Range Checkbox selected successfully!");
		} else {
		softAssert.fail("Alert - encountered an issue while selecting the Price Range checkbox!");
		}
		customWait();		
		mssp.selectProduct();
		LoginPage lp = mssp.addProductToWL();
		lp.clickLogin();
		lp.clickContinue();
		Boolean bannerDisplayStatus = lp.verifyPostLoginBanner();
		customWait();
		if(bannerDisplayStatus) {
		Allure.step("Post Login banner appears successfully!");
		} else {
		softAssert.fail("Alert - encountered an issue during login!");
		}
		softAssert.assertAll();
		}
	

}
