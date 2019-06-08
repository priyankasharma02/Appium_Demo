package appium;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.gargoylesoftware.htmlunit.History;


public class Calculator {
WebDriver driver;
String prefix_id = "com.sec.android.app.popupcalculator:";

@BeforeClass
public void setUp() throws MalformedURLException{
	//Set up desired capabilities and pass the Android app-activity and app-package to Appium
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("BROWSER_NAME", "Android");
	capabilities.setCapability("VERSION", "8.0.0"); 
	capabilities.setCapability("deviceName","07ced88c");
	capabilities.setCapability("platformName","Android");
 
   
   capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
// This package name of your app (you can get it from apk info app)
	capabilities.setCapability("appActivity","com.sec.android.app.popupcalculator.Calculator"); // This is Launcher activity of your app (you can get it from apk info app)
//Create RemoteWebDriver instance and connect to the Appium server
 //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
   driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
}

@Test
public void Test01_checkSum() throws Exception {
   //locate the Text on the calculator by using By.name()
   WebElement two=driver.findElement(By.id(prefix_id+"id/bt_02"));
   two.click();
   WebElement plus=driver.findElement(By.id(prefix_id+"id/bt_add"));
   plus.click();
   WebElement four=driver.findElement(By.id(prefix_id+"id/bt_04"));
   four.click();
   WebElement equalTo=driver.findElement(By.id(prefix_id+"id/bt_equal"));
   equalTo.click();
   //locate the edit box of the calculator by using By.tagName()
   WebElement results=driver.findElement(By.id(prefix_id+"id/txtCalc"));
	//Check the calculated value on the edit box
assert results.getText().equals("6"):"Actual value is : "+results.getText()+" did not match with expected value: 6";
System.out.println("[Assert Passed]: Actual value matches the expected value: 6 ");
}

@Test
public void Test02_checkHistoryTabIsPresent() throws Exception {
	
	WebElement history = driver.findElement(By.id(prefix_id+"id/history_button"));
	Assert.assertTrue(history.isDisplayed(), "[Assert Failed]: History tab is not present");
	System.out.println("[Assert Passed]: History tab is present");
}

@Test
public void Test03_checkCButtonClearsInput() throws Exception {
	
	WebElement two=driver.findElement(By.id(prefix_id+"id/bt_02"));
	   two.click();
	WebElement four=driver.findElement(By.id(prefix_id+"id/bt_04"));
	   four.click(); 
	WebElement eight=driver.findElement(By.id(prefix_id+"id/bt_08"));
	   eight.click(); 
	WebElement beforeClearResults=driver.findElement(By.id(prefix_id+"id/txtCalc"));
	assert beforeClearResults.getText().equals("248"):"Actual value is : "+beforeClearResults.getText()+" did not match with expected value: 248";
	WebElement C=driver.findElement(By.id(prefix_id+"id/bt_clear"));
	   C.click(); 
	WebElement afterClearResults=driver.findElement(By.id(prefix_id+"id/txtCalc"));
	assert afterClearResults.getText().equals(""):"C button did not clear input";
	System.out.println("[Assert Passed]: Clicking C button clears the input");
}

@AfterClass
public void teardown(){
	//close the app
	driver.quit();
}
}