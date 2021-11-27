package Resources;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentSparkReporter reporter;
	
	public static ExtentReports extent;
	
	public static ExtentReports getReportObject() {
	  
		String file = System.getProperty("user.dir")+"/reports/index.html";
		
		reporter = new ExtentSparkReporter(file);
		
		reporter.config().setDocumentTitle("RestAPI-Results");
		
		reporter.config().setReportName("Test Results");
		
		extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Software Test Engineer","Viraj D. Utekar");
		
		return extent;
	}
	
}
