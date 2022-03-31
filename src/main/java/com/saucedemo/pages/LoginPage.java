package com.saucedemo.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.saucedemo.actiondriver.Action1;
import com.saucedemo.base.BaseClass;

public class LoginPage extends BaseClass {

	Action1 action=new Action1();


	@FindBy(name = "user-name") 
	private WebElement userName;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(id="login-button")
	private WebElement loginButton;

	/**
	 * Initializing elements using Page Factory
	 */
	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void verifyLoginPage()
	{
		Assert.assertEquals(action.getCurrentUrl(),getUrl());
		getDriver().getTitle();
	}
	/**
	 * Login using user name and password from config.properties file
	 * under Config folder,initialized in BaseClass
	 */
	public void login() {

		action.sendKeysTo(userName,getUserName());
		action.sendKeysTo(password,getPassword());
		action.clickOnElement(loginButton);
		action.pageLoadWait();





	}


}
