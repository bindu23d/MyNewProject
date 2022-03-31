package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.actiondriver.Action1;
import com.saucedemo.base.BaseClass;

public class ProductDetailPage extends BaseClass {
	Action1 action=new Action1();

	@FindBy(className = "btn_inventory")
	private WebElement addToCartButton;
	@FindBy(className = "inventory_details_price")
	private WebElement price;
	@FindBy(className = "inventory_details_name")
	private WebElement itemName;
	@FindBy(className = "shopping_cart_link")
	private WebElement cartLink;

	public ProductDetailPage() {
		PageFactory.initElements(getDriver(), this);
	}
	public void addItemToCart()
	{
		action.clickOnElement(addToCartButton);
	}
	public void clickOnCart()
	{
		action.clickOnElement(cartLink);
		
	}


}
