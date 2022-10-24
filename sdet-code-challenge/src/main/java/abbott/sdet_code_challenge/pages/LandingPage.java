package abbott.sdet_code_challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author shraddha.gada
 *
 */
public class LandingPage {
	WebDriver driver;
	// find WebElement
	@FindBy(id = "countryDropdownBtn")
	WebElement countryDropdown;

	@FindBy(id = "languageDropdownBtn")
	WebElement languageDropdown;

	@FindBy(id = "modalSubmit")
	WebElement submitButton;

	@FindBy(id = "truste-consent-button")
	WebElement acceptCookie;

	@FindBy(id = "patHeaderLink")
	WebElement patientsLink;

	@FindBy(id = "proHeaderLink")
	WebElement professionalsLink;

	@FindBy(id = "languageText")
	WebElement languageText;

	public LandingPage(WebDriver driver) {
		//initialize web elements
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	//Getter methods
	public WebElement getCountryDropdown() {
		return countryDropdown;
	}

	public WebElement getLanguageDropdown() {
		return languageDropdown;
	}

	public WebElement getPatientsLink() {
		return patientsLink;
	}

	public WebElement getProfessionalsLink() {
		return professionalsLink;
	}

	public WebElement getAcceptCookie() {
		return acceptCookie;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getLanguageText() {
		return languageText;
	}

	// Check if Patients tab is present
	public boolean isPatientsTabPresent() {
		int count = driver.findElements(By.id("patHeaderLink")).size();
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	// Check if Professional tab is present
	public boolean isProfessionalsTabPresent() {
		int count = driver.findElements(By.id("proHeaderLink")).size();
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
}
