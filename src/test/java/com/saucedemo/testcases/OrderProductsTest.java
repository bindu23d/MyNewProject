package com.saucedemo.testcases;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.saucedemo.base.BaseClass;
import com.saucedemo.pages.AddToCartPage;
import com.saucedemo.pages.CheckOutPage;
import com.saucedemo.pages.ContinueCheckOutPage;
import com.saucedemo.pages.FinalPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.PlaceOrderPage;

public class OrderProductsTest extends BaseClass {
	private static ExtentReports extent;
	private static ExtentTest test;
	LoginPage loginPage;
	AddToCartPage addToCartPage;
	CheckOutPage checkOutPage;
	ContinueCheckOutPage continueCheckOutPage;
	PlaceOrderPage placeOrderPage;
	FinalPage finalPage;
	
	
	@BeforeSuite
	public void extentRepo()
	{
		extent=extentReportMethod();
	}
	
	@BeforeMethod
	public void setup() {
		
    
		setBrowser(); 
	}
	
	@Test
	public void addRemoveVerifyAndPlaceOrder() throws Throwable
		
		{
		loginPage=new LoginPage();
		addToCartPage=new AddToCartPage();
		checkOutPage=new CheckOutPage();
		continueCheckOutPage=new ContinueCheckOutPage();
		placeOrderPage=new PlaceOrderPage();
		finalPage=new FinalPage();
		
		test = extent.createTest("Adding Products and Placing Order ");
		
		launchApp();
		test.log(Status.INFO,"Launched the Login Page");
		
		loginPage.verifyLoginPage();
		test.log(Status.INFO,"Verified Login Page");
		
		loginPage.login();
		test.log(Status.INFO,"Signed in");
		
		addToCartPage.SelectLowToHighFromDropDown();
		test.log(Status.INFO,"Selected low to high price ");
		
		addToCartPage.AddTwoItems();
		test.log(Status.INFO,"Added items");
	
		addToCartPage.cartView();
		test.log(Status.INFO,"Cart");
		
		checkOutPage.verifyCartItems();
		test.log(Status.INFO,"Verified added items are in the cart");
		
		checkOutPage.removeItemFromCart();
		test.log(Status.INFO,"Removed one item");
		
		checkOutPage.goBack();
		test.log(Status.INFO,"Back to inventory");
		
		addToCartPage.addItem();
		test.log(Status.INFO,"Added one item again");
		
		addToCartPage.cartView();
		test.log(Status.INFO,"Cart");
		
		checkOutPage.continueCheckOut();
		test.log(Status.INFO,"Checkout");
		
		continueCheckOutPage.enterDetailsAndContinue();
		test.log(Status.INFO,"Entered details");
		
		continueCheckOutPage.clickOnContinue();
		test.log(Status.INFO,"Continue");
		
        placeOrderPage.verifyCartItems();
        test.log(Status.INFO,"Verified Cart Items");
      
		placeOrderPage.verifyTotalCostOfItems();
		test.log(Status.INFO,"Verified Total Cost");
		
		placeOrderPage.placeOrder();
		test.log(Status.INFO,"Placed Order");
		
		finalPage.verifyCompleteOrderText();
		test.log(Status.INFO,"Order Placed, Test Completed");
	
}
	@AfterMethod
	public void tearDown() {
		
		getDriver().close();
		
	}
	@AfterSuite
	public void cleanUp()
	{
		afterSuite();
	}
	
}
