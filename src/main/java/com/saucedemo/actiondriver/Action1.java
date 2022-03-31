package com.saucedemo.actiondriver;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.base.BaseClass;

/**
 * This class contains reusable methods used in page objects
 * @author Bindu
 *
 */

public class Action1 extends BaseClass {

	public static WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
	public static FileInputStream fis;
	public  void waitForVisible(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void waitForNotVisible(WebElement element) {
		try {
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void waitForElementToBeClickable(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void waitForElementToBeSelected(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeSelected(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public  void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,250)");
	}

	public void clickOnElement(WebElement element) {
		waitForVisible(element);
		element.click();
	}

	public void sendKeysTo(WebElement element, String text){
		waitForVisible(element);
		element.clear();
		element.sendKeys(text);
	}
	public void selectValueFromDropDown(WebElement element, String value) {
		waitForVisible(element);
		Select option = new Select(element);
		option.selectByValue(value);
	}
	public void selectAtIndexFromDropDown(WebElement element, int index) {
		waitForVisible(element);
		Select option = new Select(element);
		option.selectByIndex(index);
	}


	public void selectValueFromDropDownByVisibleTxt(WebElement element, String value) {
		waitForVisible(element);
		Select option = new Select(element);
		option.selectByVisibleText(value);
	}
	public void implicitWait() {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void pageLoadWait() {
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}
	public String getCurrentUrl()
	{
		return getDriver().getCurrentUrl();
	}
	public String getTitle()
	{
		return getDriver().getTitle();
	}


	public XSSFSheet getDataFromExcel(String sheetName) throws IOException
	{ 

		fis = new FileInputStream(
				System.getProperty("user.dir") + "/TestData/testdata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);


		workbook.close();

		return sheet;


	}

}


