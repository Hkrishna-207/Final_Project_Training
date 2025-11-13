package listenersPack;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportsClass {

	public static ExtentReports reports;
	public static ExtentTest test;
	
	public static void createAReport() {
		reports=new ExtentReports();
		LocalDateTime timestamp=LocalDateTime.now();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd_hh-mm");
		String time=timestamp.format(formatter);
		ExtentSparkReporter spark=new ExtentSparkReporter(new File("test-output/reports/INBReports"+time+".html"));
		reports.attachReporter(spark);
	}
	
	public static void createTest(String name) {
		test=reports.createTest(name);
	}
	
	public static void endReport() {
		reports.flush();
	}
}
