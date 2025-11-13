package cognizant.com.finalProject.zigweels.com;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class LoginPage {

	private WebDriver driver;
	Set<String> windows;
	String homePageWindow;
	String homePageTitle;
	
	@FindBy(id = "forum_login_title_lg")
	WebElement loginButton;
	
	@FindBy(xpath = "//span[text()='Google']/parent::div")
	WebElement googleLogin;
	
	@FindBy(tagName = "input")
	WebElement emailField;
	
	@FindBy(xpath = "//span[text()='Next']")
	WebElement nextButton;
	
	@FindBy(id = "report_submit_close_login")
	WebElement closeLogin;
	

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		homePageWindow=driver.getWindowHandle();
		homePageTitle=driver.getTitle();
	}
	
	public void clickOnLogin() {
		loginButton.click();
	}
	
	public void loginWithGoogle() {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(googleLogin));
			googleLogin.click();
		}catch(StaleElementReferenceException e) {
			driver.findElement(By.xpath("//span[text()='Google']/parent::div")).click();
		}
		
		windows=driver.getWindowHandles();
	}
	
	public boolean setEmail(String email) throws IOException, InterruptedException {
		boolean value=true;
		for(String window:windows) {
			driver.switchTo().window(window);
			if(!driver.getTitle().equalsIgnoreCase(homePageTitle)) {
				String currentTitle=driver.getTitle();
//				emailField.sendKeys("hari123@gmail.com");
				emailField.sendKeys(email);
				nextButton.click();
				Thread.sleep(2000);
				if(driver.getTitle().equals(currentTitle))
					value=false;
				File screenShot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				LocalDateTime timestamp=LocalDateTime.now();
				DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd_hh-mm-ss");
				String time=timestamp.format(formatter);
				Files.copy(screenShot, new File("test-output/screenshots/LoginPageErrorMessage"+time+".jpeg"));
				driver.close();
				}
		}
		
		driver.switchTo().window(homePageWindow);
		Thread.sleep(1000);
		closeLogin.click();
		Thread.sleep(1000);
		return value;
	}
	
	
}
