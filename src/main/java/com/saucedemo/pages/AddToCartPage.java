package com.saucedemo.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.actiondriver.Action1;
import com.saucedemo.base.BaseClass;

public class AddToCartPage extends BaseClass {
	Action1 action=new Action1();
	private static List<String> itemName;

	@FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/div[2]/span/select")
	private WebElement sortProducts;
	@FindBy(className = "inventory_item")
	private List<WebElement> items;
	@FindBy(id="react-burger-menu-btn")
	private WebElement menuButton;
	@FindBy(id="inventory_sidebar_link")
	private WebElement allItemsLink;
	@FindBy(id="about_sidebar_link")
	private WebElement aboutLink;
	@FindBy(id="logout_sidebar_link")
	private WebElement logoutLink;
	@FindBy(id="reset_sidebar_link")
	private WebElement resetLink;
	@FindBy(className="shopping_cart_link")
	private WebElement cartLink;


	/** 
	 * Initializing elements using Page Factory
	 */

	public AddToCartPage() {
		PageFactory.initElements(getDriver(), this);
	}


	/**
	 * Selecting low to high price from drop down
	 */

	public void SelectLowToHighFromDropDown() throws Throwable
	{
		String text="Price (low to high)";
		action.selectValueFromDropDownByVisibleTxt(sortProducts,text);
		action.implicitWait();

	}

	/**
	 * Adding two items and saving their names in the list to verify later
	 * @param firstItemAtIndex
	 * @param secondItemAtIndex
	 * @throws Throwable 
	 */

	public void AddTwoItems() throws Throwable {

		itemName=new ArrayList<String>();
		int firstItemAtIndex=(int) action.getDataFromExcel("itemIndex").getRow(0).getCell(0).getNumericCellValue();
		int secondItemAtIndex=(int) action.getDataFromExcel("itemIndex").getRow(1).getCell(0).getNumericCellValue();

		action.clickOnElement(items.get(firstItemAtIndex).findElement(By.className("btn_inventory")));
		action.clickOnElement(items.get(secondItemAtIndex).findElement(By.className("btn_inventory")));
		itemName.add(items.get(firstItemAtIndex).findElement(By.className("inventory_item_name")).getText());
		itemName.add(items.get(secondItemAtIndex).findElement(By.className("inventory_item_name")).getText());


	}

	public void addItem() throws Throwable
	{
		itemName=new ArrayList<String>();
		int atIndex=(int) action.getDataFromExcel("itemIndex").getRow(2).getCell(0).getNumericCellValue();
		action.clickOnElement(items.get(atIndex).findElement(By.className("btn_inventory")));
		itemName.add(items.get(atIndex).findElement(By.className("inventory_item_name")).getText());

	}
	public List<String> getItemNames()
	{
		return itemName;
	}
	public void removeItem(int itemAtIndex) throws Throwable
	{
		itemName=new ArrayList<String>();
		int atIndex=(int) action.getDataFromExcel("itemIndex").getRow(2).getCell(0).getNumericCellValue();
		action.clickOnElement(items.get(atIndex).findElement(By.className("btn_inventory")));

		itemName.remove(items.get(atIndex).findElement(By.className("inventory_item_name")).getText());

	}

	/**
	 * Taking to product description page
	 * @param itemAtIndex
	 */
	public void clickOnItem(int itemAtIndex)
	{

		action.clickOnElement(items.get(itemAtIndex).findElement(By.className("inventory_item_name")));
	}

	public void cartView()
	{

		action.clickOnElement(cartLink);
		action.implicitWait();

	}


}
