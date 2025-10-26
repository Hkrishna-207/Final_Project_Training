package cognizant.com.finalProject.zigweels.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestHomePage {

	WebDriver driver;
	ChromeOptions options=new ChromeOptions();
	HomePage hmp;
	UpcomingHondaBikes hondas;
	
	@BeforeClass
	public void setup() {
		options.addArguments("--disable-notifications");
		driver=new ChromeDriver(options);
		driver.get("https://www.zigwheels.com/");
		driver.manage().window().maximize();
		hmp=new HomePage(driver);
		hondas=new UpcomingHondaBikes(driver);
	}
	
	@Test
	public void newBikeList()  {
		hmp.clickOnNewBike();
		hmp.clickOnUpcomingBikes();
	}
	
	@Test(dependsOnMethods = "newBikeList")
	public void hondaBikes() throws InterruptedException {
		Thread.sleep(4000);
		hondas.clickOnHonda();
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
	}
}
