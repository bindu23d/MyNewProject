package com.saucedemo.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	private static Properties prop;
	private static WebDriver driver;
	private static ExtentReports extent;
	private static ExtentSparkReporter spark;


	//loadConfig method is to load the configuration
	@BeforeSuite 
	public  static void loadConfig() {


		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/Configuration/config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static ExtentReports extentReportMethod()
	{
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentReports/extentreport.html");


		extent = new ExtentReports();
		extent.setSystemInfo("ProjectName", "SauceDemoProject");
		extent.setSystemInfo("Author", "Bindu");
		extent.attachReporter(spark);
		return extent;

	}


	public  static void setBrowser() {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
	}
	//Maximize the screen
	public static void launchApp()
	{
		driver.manage().window().maximize();
		//Delete all the cookies
		driver.manage().deleteAllCookies();


		//Launching the URL
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}


	public static String getUrl()
	{

		return(prop.getProperty("url"));
	}

	public static String getUserName()
	{

		return(prop.getProperty("username"));
	}
	public static String getPassword()
	{

		return(prop.getProperty("password")); 
	}
	public static WebDriver getDriver()
	{
		return driver;
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}

}
