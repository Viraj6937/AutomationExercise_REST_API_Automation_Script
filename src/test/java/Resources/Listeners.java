package Resources;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends Base implements ITestListener {
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	
	ExtentTest test;
	
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	public void onTestPass(ITestResult result) {
		test.log(Status.PASS,"test case is passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}
