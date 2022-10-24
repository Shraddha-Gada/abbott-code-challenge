package abbott.sdet_code_challenge.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author shraddha.gada
 *
 */
public class DriverInitializer {
	public WebDriver driver;
	public WebDriverWait wait;
	private static Properties PROPERTIES;
	private static final String BROWSER = getProperty("browser");
	protected static final String URL = getProperty("url");

	@BeforeMethod
	public void initialize() {
		// Initialize the driver
		this.driver = initializeDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@AfterMethod
	public void shutdownDriver() {
		// Check for null in case there was an error before the driver was initialized
		if (this.driver != null) {
			this.driver.quit();
		}
	}

	protected static String getProperty(String key) {
		// Only load the properties once
		if (PROPERTIES == null) {
			try {
				// Create an object of Properties class
				PROPERTIES = new Properties();
				// Create an object of FileInputStream class with the path to properties file
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
						+ "//src//main//java//abbott//sdet_code_challenge//resources//globalVar.properties");
				// Read data from properties file
				PROPERTIES.load(fis);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return PROPERTIES.getProperty(key);
	}

	private WebDriver initializeDriver() {
		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("Chrome")) {
			// Checks for the latest version of the specified WebDriver binary. If the
			// binaries are not present on the machine, then it will download the WebDriver
			// binaries.
			WebDriverManager.chromedriver().setup();
			// instantiates the Selenium WebDriver instance with the ChromeDriver
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		// tell the webdriver to wait for a certain amount of time before it throws a
		// “NoSuchElementException”.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Maximizes the browser window
		driver.manage().window().maximize();
		// launch browser and redirect it to the Base URL
		driver.navigate().to(URL);
		return driver;

	}
}
