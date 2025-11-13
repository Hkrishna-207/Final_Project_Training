package cognizant.com.finalProject.zigweels.com;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;



public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;
    protected HomePage hmp;
    protected UpcomingHondaBikes hondas;
    protected UsedCars cars;
    protected LoginPage loginPage;

    protected static final Logger logger = Logger.getLogger(BaseTest.class.getName());
    protected FileHandler handler;

    @BeforeMethod
    public void setUpSuite() throws IOException {
        
        prop = new Properties();
        prop.load(new FileReader("resources/config/zigwheelsData.properties"));
        
        driver = DriverManager.getDriver(prop.getProperty("baseUrl"));

        handler = new FileHandler("test-output/reports/INBLog.log", true);
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);
        logger.setLevel(Level.INFO);
    }
    
    @BeforeMethod
    public void setup(ITestContext context) {
    	
    }

    @AfterSuite
    public void tearDownSuite() {
        DriverManager.quitDriver();
    }
}
