package cognizant.com.finalProject.zigweels.com;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UsedCars {

	WebDriver driver;
	
	@FindBy(xpath = "//ul/li/span[text()='MORE']")
	WebElement moreButton;
	
	@FindBy(linkText = "Used Cars")
	WebElement usedCars;
	
	@FindBy(partialLinkText = "Chennai")
	WebElement locationSearch;
	
	@FindBy(id = "priceFrom")
	WebElement minPrice;
	
	@FindBy(id = "priceTo")
	WebElement maxPrice;
	
	@FindBy(xpath = "//div[@class='gsc_thin_scroll']/ul/li//input")
	List<WebElement> brands;
	
	@FindBy(xpath = "//div[contains(@class,'col-lg-8')]/div/a")
	List<WebElement> listOfCars;
	
	@FindBy(xpath = "//a[@href='/']")
	WebElement homePage;
	
	public UsedCars(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void hoverOnMore() {
		Actions act=new Actions(driver);
		act.moveToElement(moreButton).perform();
	}
	
	public void clickOnUsedCars() {
		usedCars.click();
	}
	
	public void selectLocation(String location) {
		//LocationSearch.sendKeys(location);
		locationSearch.click();
	}
	
	public void filterPrice(String lprice,String hprice) {
		Select s=new Select(minPrice);
		s.selectByVisibleText(lprice);
		
		Select s2=new Select(maxPrice);
		s2.selectByVisibleText(hprice);
	}
	
	public void selectBrands() {
		JavascriptExecutor je=(JavascriptExecutor)driver;
		//Actions act=new Actions(driver);
		for(WebElement brand:brands) {
			//act.moveToElement(brand).click().perform();
			je.executeScript("arguments[0].scrollIntoView(true);", brand);
			je.executeScript("arguments[0].click();", brand);
		}
	}
	
	public Object[][] printCarData() throws InterruptedException {
		JavascriptExecutor je=(JavascriptExecutor)driver;
		long height=(long)je.executeScript("return document.body.scrollHeight");
		
		while(true) {
			je.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			Thread.sleep(3000);
			long ht=(long)je.executeScript("return document.body.scrollHeight");
			if(ht==height)
				break;
			height=ht;
			
		}
		
		List<String> carModels=new ArrayList<String>();
		List<String> carPrices=new ArrayList<String>();
		
		for(WebElement car:listOfCars) {
			if(Integer.parseInt(car.getAttribute("data-price"))<=400000){
//				System.out.println("Car Name: "+car.getText());
//				System.out.println("Car Price: "+car.getAttribute("data-price"));
//				System.out.println("--------------------------------------------");
				carModels.add(car.getText());
				carPrices.add(car.getAttribute("data-price"));
			}
		}
		Object[][] data=new Object[carModels.size()+1][2];
		data[0][0]="Car Model";
		data[0][1]="Car Price";
		
		for(int i=0;i<carModels.size();i++) {
			data[i+1][0]=carModels.get(i);
			data[i+1][1]=carPrices.get(i);
		}
		
		System.out.println(listOfCars.size());
		je.executeScript("window.scrollTo(0,0);");
		
		return data;
	}
	
	public void clickOnHome() {
		homePage.click();
	}
}
