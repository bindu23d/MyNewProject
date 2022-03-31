package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.saucedemo.actiondriver.Action1;
import com.saucedemo.base.BaseClass;

public class FinalPage extends BaseClass {
	Action1 action=new Action1();
	@FindBy(id = "checkout_complete_container")
	private WebElement textContainer;
	@FindBy(className = "complete-header")
	private WebElement header;
	@FindBy(id = "back-to-products")
	private WebElement backToProductsBtn;
	/**
	 * Initializing the elements using Page Factory
	 */
	public FinalPage() {
		PageFactory.initElements(getDriver(), this);
	}

	/**
	 * Verifying if order is placed
	 */
	public void verifyCompleteOrderText()
	{
		boolean isPresent=header.isDisplayed();
		Assert.assertTrue(isPresent);

	}
	public void goBackToInventory()
	{
		action.clickOnElement(backToProductsBtn);
		action.pageLoadWait();

	}

}
