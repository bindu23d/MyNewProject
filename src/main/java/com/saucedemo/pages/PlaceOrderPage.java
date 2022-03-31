package com.saucedemo.pages;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.saucedemo.actiondriver.Action1;
import com.saucedemo.base.BaseClass;

public class PlaceOrderPage extends BaseClass{

	Action1 action=new Action1();
	CheckOutPage checkOutPage=new CheckOutPage();
	private static List<String> itemNamesList;
	private static final DecimalFormat df=new DecimalFormat("0.00");

	@FindBy(className = "cart_item")
	private List<WebElement> cartProductsList;

	@FindBy(className = "summary_subtotal_label")
	private WebElement subtotalLabel;

	@FindBy(className = "summary_tax_label")
	private WebElement taxLabel;

	@FindBy(className = "summary_total_label")
	private WebElement totalLabel;
	@FindBy(id = "finish")
	private WebElement finishButton;

	public PlaceOrderPage() {
		PageFactory.initElements(getDriver(), this);
	}
	/**
	 * Getting item names from the cart on place order page, using in products verification later
	 * @return list of item names
	 */
	public List<String> getItemNamesInPlaceOrderPage()
	{
		itemNamesList=new ArrayList<String>();
		itemNamesList.clear();

		for(int i=0;i<cartProductsList.size();i++) {
			itemNamesList.add(cartProductsList.get(i).findElement(By.className("inventory_item_name")).getText());
		}
		return itemNamesList;
	}

	public int numberOfItemsInCart()
	{
		return cartProductsList.size();
	}

	public void verifyCartItems()
	{

		Assert.assertTrue(checkOutPage.itemNamesInCart().equals(getItemNamesInPlaceOrderPage()));

	}
	/**
	 * getting subtotal, tax and total by extracting $ sign 
	 * using substring function
	 */
	public float getSubtotal() 
	{
		return Float.parseFloat(subtotalLabel.getText().substring(13));
	}

	public float getTax() 
	{
		return Float.parseFloat(taxLabel.getText().substring(6));
	}

	public float getTotal()
	{
		return Float.parseFloat(totalLabel.getText().substring(8));
	}
	/**
	 * Adding the item costs casting it to double
	 * @return subtotal
	 */
	public float calculateSubTotal() 
	{
		float subTotalOfItems = 0.0f;
		for (WebElement ele: cartProductsList) {
			float itemPrice = Float.parseFloat(ele.findElement(By.className("item_pricebar")).getText().substring(1));
			subTotalOfItems = subTotalOfItems+itemPrice;

		}
		return subTotalOfItems;
	}


	/**
	 * Getting the total cost by adding items' cost and tax and 
	 * verifying with actual cost shown on the page
	 */
	public void verifyTotalCostOfItems()
	{
		float subTotal=0.0f;
		float expectedItemTotal=0.0f;
		float tax=0.0f;
		float actualitemTotal=0.0f;
		actualitemTotal=this.getTotal();
		subTotal=this.calculateSubTotal();
		tax=this.getTax();
		expectedItemTotal=subTotal+tax;

		Assert.assertEquals(df.format(actualitemTotal),df.format(expectedItemTotal));



	}

	public void placeOrder()
	{
		action.clickOnElement(finishButton);
		action.pageLoadWait();
	}

}
