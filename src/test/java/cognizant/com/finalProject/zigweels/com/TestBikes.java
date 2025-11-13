package cognizant.com.finalProject.zigweels.com;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.WriteDataToXL;


public class TestBikes extends BaseTest {

	
	@BeforeMethod
	public void setup() {
		hmp = new HomePage(driver);
		hondas=new UpcomingHondaBikes(driver);
	}
	
    @Test
    public void newBikeList() {
    	hmp = new HomePage(driver);
	
    	
        logger.info("Starting INB Testing");
        hmp.clickOnNewBike();
        logger.info("New Bikes button is clicked");
        hmp.clickOnUpcomingBikes();
        logger.info("Upcoming New Bikes button is clicked");
    }

    @Test(dependsOnMethods = "newBikeList")
    public void hondaBikes() throws Exception {
    	hondas=new UpcomingHondaBikes(driver);
        Object[][] hondaData = hondas.clickOnHonda();
        logger.info("Honda bikes button is clicked");
        Assert.assertNotNull(hondaData);
        WriteDataToXL.writeHondaData(hondaData);
    }
}