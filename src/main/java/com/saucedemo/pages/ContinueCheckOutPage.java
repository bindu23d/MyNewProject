package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.actiondriver.Action1;
import com.saucedemo.base.BaseClass;

public class ContinueCheckOutPage extends BaseClass {
	Action1 action=new Action1();
	PlaceOrderPage placeOrderPage=new PlaceOrderPage();

	@FindBy(id = "first-name")
	private WebElement firstNameField;

	@FindBy(id = "last-name")
	private WebElement lastNameField;

	@FindBy(id = "postal-code")
	private WebElement postalCodeField;

	@FindBy(className = "cart_button")
	private WebElement continueButton;
	public ContinueCheckOutPage() {
		PageFactory.initElements(getDriver(), this);
	}
	/**
	 * Entering user details to Place order
	 * @throws Throwable
	 */
	public void enterDetailsAndContinue() throws Throwable

	{
		String uname=action.getDataFromExcel("UserData").getRow(1).getCell(0).getStringCellValue();
		String lname=action.getDataFromExcel("UserData").getRow(1).getCell(1).toString();
		String zipCode=action.getDataFromExcel("UserData").getRow(1).getCell(2).toString();
		action.sendKeysTo(firstNameField, uname);
		action.sendKeysTo(lastNameField,lname);
		action.sendKeysTo(postalCodeField, zipCode);

	}

	public void clickOnContinue()
	{
		action.clickOnElement(continueButton);
		action.pageLoadWait();

	}



}
