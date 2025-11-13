package cognizant.com.finalProject.zigweels.com;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.WriteDataToXL;


public class TestCars extends BaseTest {

	@BeforeMethod
	public void setup() {
		cars = new UsedCars(driver);
	}
	
    @Test
    public void usedCars() throws Exception {
 
        cars.hoverOnMore();
        cars.clickOnUsedCars();
        logger.info("Used cars button is clicked");

        cars.selectLocation(prop.getProperty("location"));
        logger.info("Location selected: " + prop.getProperty("location"));

        cars.filterPrice(prop.getProperty("minPrice"), prop.getProperty("maxPrice"));
        logger.info("Price filters applied");

        cars.selectBrands();
        logger.info("Popular brands selected");

        Object[][] carsData = cars.printCarData();
        Assert.assertNotNull(carsData);
        WriteDataToXL.writeCarsData(carsData);
        logger.info("Filtered cars data fetched");

        cars.clickOnHome();
        logger.info("Navigated to Homepage");
    }
}