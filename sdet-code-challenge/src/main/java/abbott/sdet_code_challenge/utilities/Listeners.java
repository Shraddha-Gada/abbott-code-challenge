package abbott.sdet_code_challenge.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

//to generate logs and customize the reports
public class Listeners implements ITestListener {

	private ExtentReports extent = ExtentReporter.getReport();
	private ExtentTest test;
	private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
		test = extent.createTest(testName);
		extentTest.set(test);
		System.out.println("Test case '" + testName + "' started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
		extentTest.get().log(Status.PASS, "Test Passed");
		System.out.println("Test case '" + testName + "' passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
		extentTest.get().log(Status.FAIL, "Test Failed");
		System.out.println("Test case '" + testName + "' failed");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
