package appiumFramework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class capability {
	protected static String appactivity;
	protected static String apppackage;
	protected static String deviceName;
	protected static String platformName;
	protected static String chromedriver;
	public AppiumDriverLocalService Service;
	
	public AppiumDriverLocalService startServer()
	{
		/*Service = AppiumDriverLocalService.buildDefaultService();
		Service.start();
		return Service; */
		boolean flag = checkifserverisRunning(4723);
		if(!flag) {
		
						Service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
		                .usingDriverExecutable(new File("c:\\Program Files\\nodejs\\node.exe"))
		                .withAppiumJS(new File("C:\\Users\\IBMADMIN\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
		                .withIPAddress("127.0.0.1").usingPort(4723));		                
		                
		                Service.start();
					}
	                return Service;
	    
	}

	public static boolean checkifserverisRunning(int port)
    {
        boolean isServerRunning=false;
        ServerSocket serversocket;
        try{
            serversocket = new ServerSocket(port);
            serversocket.close();
        }
        catch(IOException e)
        {
            isServerRunning=true;
        }
        finally{
            serversocket=null;
        }
        return isServerRunning;
        
    }
	
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\resources\\emulator.bat");
		Thread.sleep(5000);
	}

	public static AndroidDriver<AndroidElement> capabilities(String appactivity, String apppackage, String deviceName, String platformName, String chromedriver) throws IOException, InterruptedException {
		FileReader fis = new FileReader(System.getProperty("user.dir")+"//src//main//resources//global.properties");
		Properties pro = new Properties();
		pro.load(fis);
		
		appactivity = pro.getProperty("appActivity");
		apppackage = pro.getProperty("appPackage");
		deviceName = pro.getProperty("deviceName");
		platformName = pro.getProperty("platformName");
		chromedriver = pro.getProperty("CHROMEDRIVER_EXECUTABLE");
		
		if(deviceName.contains("Ashok Android")) {
			startEmulator();
		}
			
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, apppackage);
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appactivity);
        cap.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, chromedriver);
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
        return driver;

	}

}
