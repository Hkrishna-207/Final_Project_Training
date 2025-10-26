package cognizant.com.finalProject.zigweels.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath  = "//span[text()='NEW BIKES']")
	WebElement newBike;
	
	@FindBy(xpath = "//a[@title='Upcoming Bikes']")
	WebElement upcomingBikes;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public void clickOnNewBike() {
		Actions act=new Actions(driver);
		act.moveToElement(newBike).perform();;
	}
	
	public void clickOnUpcomingBikes() {
		upcomingBikes.click();
	}
}
