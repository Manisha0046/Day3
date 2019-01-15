package Practice.Day3;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Hello world!
 *
 */
public class DesiredCapbility 
{
	WebDriver driver;
   @BeforeMethod
   public void BeforeTest()
   {
	   DesiredCapabilities capbilities=DesiredCapabilities.chrome();
	   capbilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);   
	   System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
	   driver=new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	   driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	   
   }
   @Test
   public void Tets_SSL_Certificate()
   {
	   driver.get("https://cacert.org/");
   }
   
   @AfterMethod
   public void AfterTest()
   {
	   driver.close();
   }
}
