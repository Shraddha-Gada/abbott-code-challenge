package abbott.sdet_code_challenge.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import abbott.sdet_code_challenge.pages.LandingPage;
import abbott.sdet_code_challenge.utilities.DriverInitializer;

/**
 * @author shraddha.gada
 *
 */
public class LandingPageTest extends DriverInitializer {
	
	private String country1 = getProperty("country1");
	private String language1 = getProperty("language1");
	private String country2 = getProperty("country2");
	private String language2 = getProperty("language2");

	@Test(testName = "TC.1 Verify landing page for country 'US'")
	public void testLandingPageUS() {

		LandingPage lp = new LandingPage(driver);
		driver.navigate().refresh();
		// Select the required country from the dropdown
		lp.getCountryDropdown().click();
		driver.findElement(By.xpath("//li[contains(.,\'" + country1 + "\')]")).click();
		// Select the required language from the dropdown
		lp.getLanguageDropdown().click();
		driver.findElement(By.xpath("//li[contains(.,\'" + language1 + "\')]")).click();
		// Click Submit
		lp.getSubmitButton().click();
		// Accept Cookie
		lp.getAcceptCookie().click();
		// Validate if correct language is selected
		Assert.assertEquals("US: English", lp.getLanguageText().getText());
		// Verify presence of patients tab
		Assert.assertTrue(lp.isPatientsTabPresent(), "Patients Tab is not present for "+country1);
		System.out.println("Patients Tab is present for "+country1);
		// Verify presence of professionals tab		
		Assert.assertTrue(lp.isProfessionalsTabPresent(), "Professionals Tab is not present for "+country1);
		System.out.println("Professionals Tab is present for "+country1);

	}

	@Test(testName = "TC.2 Verify landing page for country 'France'")
	public void testLandingPageFrance() {
		LandingPage lp = new LandingPage(driver);
		driver.navigate().refresh();
		lp.getLanguageText().click();
		// Select the required country from the dropdown
		lp.getCountryDropdown().click();
		driver.findElement(By.xpath("//li[contains(.,\'" + country2 + "\')]")).click();
		// Select the required language from the dropdown
		lp.getLanguageDropdown().click();
		driver.findElement(By.xpath("//li[contains(.,\'" + language2 + "\')]")).click();
		// Click Submit
		lp.getSubmitButton().click();
		// Accept Cookie
		lp.getAcceptCookie().click();
		// Verify presence of patients and professionals tab
		Assert.assertFalse(lp.isPatientsTabPresent(), "Patients Tab is  present for "+country2);
		System.out.println("Patients Tab is not present for France");
		Assert.assertFalse(lp.isProfessionalsTabPresent(), "Professionals Tab is present for "+country2);
		System.out.println("Professionals Tab is not present for "+country2);

	}

}
