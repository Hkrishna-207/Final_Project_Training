package cognizant.com.finalProject.zigweels.com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class UpcomingHondaBikes {

	WebDriver driver;
	
	//@FindBy(xpath = "//a[text()='Honda']")
	@FindBy(linkText = "Honda")
	WebElement honda;
	
	  
	
	
	public UpcomingHondaBikes(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	public void clickOnHonda() {
		
//		JavascriptExecutor je=(JavascriptExecutor)driver;
//		je.executeScript("window.scrollTo(0,500);");
//		driver.findElement(By.linkText("Honda"))
		Actions act=new Actions(driver);
		act.moveToElement(honda).click().perform();
		
		List<WebElement> hondaList=driver.findElements(By.xpath("//ul[@id='modelList']/li"));
		for(WebElement element:hondaList) {
			System.out.println(element.getText());
		}
		
		List<WebElement> bikeModel=driver.findElements(By.xpath("//ul[@id='modelList']/li/div/div/a"));
		List<WebElement> bikePrice=driver.findElements(By.xpath("//ul[@id='modelList']/li/div/div/div[1]"));
		List<WebElement> expLaunchDate=driver.findElements(By.xpath("//ul[@id='modelList']/li/div/div/div[2]"));
		
		System.out.println("==================================");
		
		for(int i=0;i<hondaList.size();i++) {
			System.out.println("Bike Model: "+bikeModel.get(i).getText());
			System.out.println("Bike Price: "+bikePrice.get(i).getText());
			System.out.println("Expected Launch Date: "+expLaunchDate.get(i).getText());
			System.out.println("-------------------");
		}
	}
}
