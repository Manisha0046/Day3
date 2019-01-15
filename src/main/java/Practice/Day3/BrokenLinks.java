package Practice.Day3;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrokenLinks {

	WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		//DesiredCapabilities capbitity=DesiredCapabilities.chrome();
	//	capbitity.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/test/newtours/");
	}
	
	@Test
	public void Broken_links() throws IOException
	{
		List<WebElement> links=driver.findElements(By.tagName("a"));
		Iterator<WebElement> it=links.iterator();
		while(it.hasNext())
		{
			String str=it.next().getAttribute("href");
			URL url=new URL(str);
			HttpURLConnection connection = null;
			try 
			{
				connection =  (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(3000);
				connection.connect();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			if(connection.getResponseCode()==200)
			{
				System.out.println(str+"==>"+connection.getResponseMessage());
			}
			if(connection.getResponseCode()>404)
			{
				System.out.println(str+"==>"+connection.getResponseMessage());
			}
			
		}
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
}
