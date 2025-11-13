package cognizant.com.finalProject.zigweels.com;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.ReadDataFromXL;


public class TestLogin extends BaseTest {
	
	ReadDataFromXL readLoginData;
	
	@BeforeMethod
	public void setup() {
		
        loginPage = new LoginPage(driver);
        
	}
	
	@DataProvider(name="LoginData")
	public Object[] readData() throws IOException {
		readLoginData=new ReadDataFromXL();
		return readLoginData.getLoginData();
	}

    @Test(dataProvider = "LoginData")
    public void loginTest(String email) throws Exception {
    	
        loginPage.clickOnLogin();
        logger.info("Login button clicked");

        loginPage.loginWithGoogle();
        logger.info("Login with Google initiated");

        boolean value = loginPage.setEmail(email);
        Assert.assertTrue(value);
        logger.info("Email entered and next button clicked");
    }
}
