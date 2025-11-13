package listenersPack;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class ListenerClass implements ITestListener{

	WebDriver driver;
	
	@Override
	public void onStart(ITestContext context) {
		ReportsClass.createAReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		ReportsClass.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		ReportsClass.test.log(Status.PASS, "The "+result.getMethod().getMethodName()+" is Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		ReportsClass.test.log(Status.FAIL, "The "+result.getMethod().getMethodName()+" is Failed");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		ReportsClass.endReport();
	}
}
