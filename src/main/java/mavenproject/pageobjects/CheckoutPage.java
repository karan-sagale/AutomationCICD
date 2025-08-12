package mavenproject.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import mavenproject.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver; // LandingPage class driver variable

	public CheckoutPage(WebDriver driver) { // StanAlone class driver variable

		super(driver); // To send back the driver to AbstractComponent Parent Class

		this.driver = driver; // Assigned StandAlone class driver to this (i.e. LandingPage) class driver
								// variable
		PageFactory.initElements(driver, this);
		// To initialize WebElements using FindBy annotation of Page Object Model and
		// Page Factory pattern.
		// It is used to declare and locate web elements within a Page Object class.

	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);

		a.sendKeys(country, countryName).build().perform();

		waitForElementToAppear(results);

		selectCountry.click();

	}

	public ConfirmationPage submitOrder() {

		submit.click();

		return new ConfirmationPage(driver);
	}

}
