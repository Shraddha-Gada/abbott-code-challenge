package abbott.sdet_code_challenge.utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
/**
 * @author shraddha.gada
 *
 */
public class ExtentReporter {
	static ExtentReports extent;

	public static ExtentReports getReport() {
		String path = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Abbott Test Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Shraddha Gada");
		return extent;
	}

}
