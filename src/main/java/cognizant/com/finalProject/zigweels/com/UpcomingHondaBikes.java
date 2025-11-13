package cognizant.com.finalProject.zigweels.com;

import java.util.ArrayList;
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
	
	
	@FindBy(linkText = "Honda")
	WebElement honda;
	
	  
	
	
	public UpcomingHondaBikes(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	public Object[][] clickOnHonda() {

		Actions act=new Actions(driver);
		act.moveToElement(honda).click().perform();
		
		List<WebElement> hondaList=driver.findElements(By.xpath("//ul[@id='modelList']/li"));
		
		List<WebElement> bikeModel=driver.findElements(By.xpath("//ul[@id='modelList']/li/div/div/a"));
		List<WebElement> prices=driver.findElements(By.xpath("//ul[@id='modelList']/li/div/div/div[1]"));
		List<WebElement> expLaunchDate=driver.findElements(By.xpath("//ul[@id='modelList']/li/div/div/div[2]"));
		
		System.out.println("==================================");
		
		List<String> bikeModels=new ArrayList<String>();
		List<String> bikePrices=new ArrayList<String>();
		List<String> launchDate=new ArrayList<String>();
		
		for(int i=0;i<hondaList.size();i++) {
			
			if((Integer.parseInt(hondaList.get(i).getAttribute("data-price")))<400000){
//				System.out.println("Bike Model: "+bikeModel.get(i).getText());
//				System.out.println("Bike Price: "+prices.get(i).getText());
//				System.out.println(expLaunchDate.get(i).getText());
//				System.out.println("-------------------");
				bikeModels.add(bikeModel.get(i).getText());
				bikePrices.add(prices.get(i).getText());
				launchDate.add(expLaunchDate.get(i).getText());
		
			}
		}
		Object[][] data=new Object[bikeModels.size()+1][3];
		data[0][0]="Bike Model";
		data[0][1]="Bike Price";
		data[0][2]="Expected Launch";
		
		for(int i=0;i<bikeModels.size();i++) {
			data[i+1][0]=bikeModels.get(i);
			data[i+1][1]=bikePrices.get(i);
			data[i+1][2]=launchDate.get(i);
		}
		return data;
	}
}
