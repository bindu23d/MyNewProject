package com.saucedemo.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.saucedemo.actiondriver.Action1;
import com.saucedemo.base.BaseClass;

public class CheckOutPage extends BaseClass
{
	AddToCartPage addToCartPage=new AddToCartPage();
	Action1 action=new Action1();

	private static List<String> cartItemNames;

	@FindBy(id="continue-shopping")
	private WebElement continueShoppingButton;

	@FindBy(id="checkout")
	private WebElement checkoutButton;

	@FindBy(className="cart_item")
	private List<WebElement> cartProducts;

	/**
	 * Initializing the elements using Page Factory
	 */
	public CheckOutPage() {
		PageFactory.initElements(getDriver(),this);
	}

	/**
	 * Going back to inventory
	 */
	public void goBack() {
		action.clickOnElement(continueShoppingButton);
		action.pageLoadWait();

	}


	public void removeItemFromCart() throws Throwable
	{   
		int atindex=(int) action.getDataFromExcel("itemIndex").getRow(1).getCell(0).getNumericCellValue();
		action.clickOnElement(cartProducts.get(atindex).findElement(By.className("cart_button")));

	}
	/**
	 * Getting item names from the cart
	 * @return List of item names
	 */
	public List<String> itemNamesInCart() 
	{
		cartItemNames=new ArrayList<String>();
		cartItemNames.clear();
		for(int i=0;i<cartProducts.size();i++) {
			cartItemNames.add(cartProducts.get(i).findElement(By.className("inventory_item_name")).getText());
		}
		return cartItemNames;
	}

	public int totalItems()
	{
		return cartProducts.size();
	}

	/**
	 * Verifying if added items are in cart
	 */
	public void verifyCartItems()
	{

		Assert.assertEquals(addToCartPage.getItemNames(),itemNamesInCart());

	}

	public void continueCheckOut()
	{
		action.clickOnElement(checkoutButton);
		action.pageLoadWait();


	}


}
