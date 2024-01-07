package test_reusable_components;

import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;

public class TestListener implements ITestListener{
	
		public void onTestSuccess(ITestResult result) {
		Allure.step("Hurray! - All Teststeps have Passed");			
		}
		
		public void onTestFailure(ITestResult result) {
		Allure.step("Ooops! - Looks like one or more Tests have Failed");			
		}
		
		public void onTestSkip(ITestResult result) {
		Allure.step("Alert! - Looks like one or more Tests have been Skipped");		
		}

}
