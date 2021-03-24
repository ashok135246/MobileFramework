package appiumFramework;

import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
public class Demos extends capability{
AndroidDriver<AndroidElement> driver;
	
	@BeforeTest
	public void bt() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("taskkill /f /im node.exe");
		Thread.sleep(5000);
		
	}
	
	//Positive scenario
	@Test(enabled=false)
	public void testcase1() 
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ashok");
		driver.findElement(By.xpath("//*[@text='Male']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");
		driver.findElement(By.xpath("//*[@text='Australia']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
				
	}
	//Negative scenario with error message
	@Test(enabled=false)
	public void testcase2()
	{
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ashok");
		driver.findElement(By.xpath("//*[@text='Male']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");
		driver.findElement(By.xpath("//*[@text='Australia']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String errmsg = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		System.out.println(errmsg);
		Assert.assertEquals(errmsg, "Please enter your name");
	}
	
	@Test(enabled=false)
	public void testcase3() throws InterruptedException
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ashok");
		driver.findElement(By.xpath("//*[@text='Male']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");
		driver.findElement(By.xpath("//*[@text='Australia']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Air jordan 9 Retro\"));");
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Air Jordan 9 Retro\"))");
        int noofproducts = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		System.out.println(noofproducts);
		for (int i=0;i<noofproducts;i++)
		{
			String Name = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();             
			//this condition will go inside only if it finds Air Jordon 9 retro             
			if(Name.equals("Air Jordan 9 Retro"))             
			{                 
				//i should get the i value and then click on the ith value                  
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();                 
				break;
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(3000);
		String cartprod = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(cartprod, "Air Jordan 9 Retro");
	}
	
	@Test(enabled=false)
	public void testcase4() throws InterruptedException
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ashok");
		driver.findElement(By.xpath("//*[@text='Male']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");
		driver.findElement(By.xpath("//*[@text='Australia']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Nike SFB Jungle\").instance(0))");
        int noofproducts = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		System.out.println(noofproducts);
		for (int i=0;i<noofproducts;i++)
		{
			String Name = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();			           
			if(Name.equals("Nike SFB Jungle"))             
			{                 
				//i should get the i value and then click on the ith value                  
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();                
				break;
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(3000);
		String cartprod = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(cartprod, "Nike SFB Jungle");
	}
	
	@Test
	public void testcase5() throws InterruptedException, IOException
	{
		Service= startServer();
		driver = capabilities(appactivity, apppackage, deviceName, platformName, chromedriver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ashok");
		driver.findElement(By.xpath("//*[@text='Male']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));");
		driver.findElement(By.xpath("//*[@text='Australia']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		/*click on 1st add cart button
		driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click(); 
		//click on 2nd add cart button
		driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(1).click(); */
		
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        //this is to click on the second add to cart button 
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);
        
        String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
        //This is to remove the & symbol from the amount which is in first place
        amount1=amount1.substring(1);
        Double amount1value = Double.parseDouble(amount1);
        System.out.println(amount1);
        String amount2 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
        amount2=amount2.substring(1);
        Double amount2value = Double.parseDouble(amount2);
        System.out.println(amount2);
        
        //I am adding the two product
        Double TotalExpectedAmount = amount1value + amount2value;
        System.out.println(TotalExpectedAmount);
        
        String ActualTotalAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        ActualTotalAmount = ActualTotalAmount.substring(1);
        Double ActualAmountFinal = Double.parseDouble(ActualTotalAmount);
        System.out.println(ActualAmountFinal);
        //comparing the two product price with total price
        Assert.assertEquals(ActualAmountFinal, TotalExpectedAmount);
        //complete this page and move on
        //checkbox I am performing tap option
        
        WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));
        TouchAction t = new TouchAction(driver);
        t.tap(tapOptions().withElement(element(checkbox))).perform();
        
      /*  WebElement tc = driver.findElement(By.xpath("//*[@text='Please read our terms and conditions']"));
        t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(3))).release().perform();
        driver.findElement(By.id("android:id/button1")).click(); */
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(8000);
        
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
        }
        //its moving my context to webapp and testing google page
        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElement(By.xpath("//*[@name='q']")).sendKeys("IBM");
        driver.findElement(By.xpath("//*[@name='q']")).sendKeys(Keys.RETURN);
        Thread.sleep(5000);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
        Service.stop();
        
	}
	
	@AfterTest
	public void at() throws IOException
	{
		Runtime.getRuntime().exec("taskkill /F /IM qemu-system-i386.exe");
	}

}
